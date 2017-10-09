package org.fxb.boot.config;

import org.fxb.config.Config;
import org.fxb.security.handlers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.util.StringUtils;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 5. 30.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true)
public class SecurityConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	private Config config;

	public void setConfig(Config config) {
		this.config = config;
	}

	@Configuration
	public static class SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		@Autowired
		private Config config;

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth
					.inMemoryAuthentication()
					.withUser("admin").password("1234").roles("USER");
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			for (String resourceHandler : StringUtils.delimitedListToStringArray(config.getString("security.resourceHandlersIgnored"), ",")) {
				web.ignoring().antMatchers(resourceHandler);
			}
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			String defaultTargetUrl = config.getString("security.defaultTargetUrl");
			// 리다이렉트에 사용될 직접 입력한 url 값을 저장된 파라메테명.
			String targetUrlParameter = config.getString("security.targetUrlParameter");
			boolean alwaysUseDefaultTargetUrl = config.getBoolean
					("security.alwaysUseDefaultTargetUrl");
			// 헤더의 referer 값 사용여부
			boolean useReferer = config.getBoolean("security.useReferer");

			String errorPageUrl = config.getString("security.errorPageUrl");

			String loginUrl = config.getString("security.loginUrl");
			String usernameParameter = config.getString("security.usernameParameter");
			String passwordParameter = config.getString("security.passwordParameter");
			String loginProcessingUrl = config.getString("security.loginProcessingUrl");
			String logoutUrl = config.getString("security.logoutUrl");
			String logoutProcessingUrl = config.getString("security.logoutProcessingUrl");
			String[] cleanCookies = StringUtils.delimitedListToStringArray(config.getString("security.cleanCookies"), ",");
			String rememberMeCookieName = config.getString("security.rememberMeCookieName");

			UnauthorizedAccessHandler unauthorizedAccessHandler = new UnauthorizedAccessHandler(loginUrl);

			LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
			loginSuccessHandler.setTargetUrlParameter(targetUrlParameter);
			loginSuccessHandler.setAlwaysUseDefaultTargetUrl(alwaysUseDefaultTargetUrl);
			loginSuccessHandler.setDefaultTargetUrl(defaultTargetUrl);
			loginSuccessHandler.setUseReferer(useReferer);

			LogoutSuccessHandler logoutSuccessHandler = new LogoutSuccessHandler();
			logoutSuccessHandler.setAlwaysUseDefaultTargetUrl(alwaysUseDefaultTargetUrl);
			logoutSuccessHandler.setDefaultTargetUrl(defaultTargetUrl);
			logoutSuccessHandler.setTargetUrlParameter(targetUrlParameter);

			LoginFailureHandler loginFailureHandler = new LoginFailureHandler();
			loginFailureHandler.setDefaultFailureUrl(defaultTargetUrl);

			AccessFailureHandler accessFailureHandler = new AccessFailureHandler(loginUrl, errorPageUrl);
			accessFailureHandler.setRedirect(config.getBoolean("security.accessFailureRedirect"));

			http
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.valueOf(config.getString("security.sessionCreationPolicy")))

					.and()
					.formLogin()
					.usernameParameter(usernameParameter)
					.passwordParameter(passwordParameter)
					.loginPage(loginUrl).permitAll()
					.loginProcessingUrl(loginProcessingUrl).permitAll()
					.failureHandler(loginFailureHandler)
					.successHandler(loginSuccessHandler)

					.and()
					.logout()
					// 로그아웃할때 세션을 제거한다.
					.invalidateHttpSession(config.getBoolean("security.invalidateHttpSession"))
					.logoutUrl(logoutUrl)
					.logoutSuccessUrl(logoutProcessingUrl)
					.logoutSuccessHandler(logoutSuccessHandler)
					.deleteCookies(cleanCookies)

					.and().exceptionHandling().authenticationEntryPoint(unauthorizedAccessHandler)
					.and().exceptionHandling().accessDeniedHandler(accessFailureHandler)

					.and()
					.rememberMe()
					.key(rememberMeCookieName)

					.and()
					.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
					.and()
					.authorizeRequests()
					.and().httpBasic();
		}
	}

}