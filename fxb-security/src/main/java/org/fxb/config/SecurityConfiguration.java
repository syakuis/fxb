package org.fxb.config;

import org.fxb.security.handlers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 5. 30.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true)
public class SecurityConfiguration extends GlobalMethodSecurityConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	private Config config;

	public void setConfig(Config config) {
		this.config = config;
	}

//	@Override
//	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder
//				.inMemoryAuthentication()
//				.withUser("admin").password("1234").roles("USER");
//	}

	@Bean("filterAccessDecisionManager")
	public AccessDecisionManager filterAccessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
		decisionVoters.add(new WebExpressionVoter());
		decisionVoters.add(new RoleVoter());
		decisionVoters.add(new AuthenticatedVoter());
		return new AffirmativeBased(decisionVoters);
	}

	@Bean
	public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource() {
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();
		requestMap.put(new AntPathRequestMatcher("/member/user"), SecurityConfig.createList("ROLE_USER"));
		requestMap.put(new AntPathRequestMatcher("/member/user"), SecurityConfig.createList("ROLE_USER"));
		return new DefaultFilterInvocationSecurityMetadataSource(requestMap);
	}


	@Configuration
	public static class SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		private AuthenticationManager authenticationManager;
		private FilterSecurityInterceptor filterSecurityInterceptor;

		@Autowired
		private Config config;

		@Autowired
		private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

		@Autowired
		private AccessDecisionManager filterAccessDecisionManager;

		@Autowired
		public void registerGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			authenticationManagerBuilder
					.inMemoryAuthentication()
					.withUser("admin").password("1234").roles("USER");
		}

		@Bean(name = "authenticationManager")
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			this.authenticationManager = super.authenticationManagerBean();
			return this.authenticationManager;
		}

		@Bean
		@DependsOn("authenticationManager")
		public FilterSecurityInterceptor filterSecurityInterceptor() {
			this.filterSecurityInterceptor = new FilterSecurityInterceptor();
			filterSecurityInterceptor.setAccessDecisionManager(filterAccessDecisionManager);
			filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
			filterSecurityInterceptor.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);

			return this.filterSecurityInterceptor;
		}

		@Bean
		@DependsOn("filterSecurityInterceptor")
		public WebInvocationPrivilegeEvaluator privilegeEvaluator() {
			return new DefaultWebInvocationPrivilegeEvaluator(filterSecurityInterceptor);
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			for (String resourceHandler : StringUtils.delimitedListToStringArray(config.getString("security.resourceHandlersIgnored"), ",")) {
				web.ignoring().antMatchers(resourceHandler);
			}

			web.ignoring().antMatchers("/favicon.ico");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			String defaultTargetUrl = config.getString("security.defaultTargetUrl");
			// 리다이렉트에 사용될 직접 입력한 url 값을 저장된 파라메테명.
			String targetUrlParameter = config.getString("security.targetUrlParameter");
			boolean alwaysUseDefaultTargetUrl = config.getBoolean
					("security.alwaysUseDefaultTargetUrl");
			// 헤더의 referrer 값 사용여부
			boolean useReferrer = config.getBoolean("security.useReferrer");

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
			unauthorizedAccessHandler.setRealmName("fxb-security-realm");

			LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
			loginSuccessHandler.setTargetUrlParameter(targetUrlParameter);
			loginSuccessHandler.setAlwaysUseDefaultTargetUrl(alwaysUseDefaultTargetUrl);
			loginSuccessHandler.setDefaultTargetUrl(defaultTargetUrl);
			loginSuccessHandler.setUseReferer(useReferrer);

			LogoutSuccessHandler logoutSuccessHandler = new LogoutSuccessHandler();
			logoutSuccessHandler.setAlwaysUseDefaultTargetUrl(alwaysUseDefaultTargetUrl);
			logoutSuccessHandler.setDefaultTargetUrl(defaultTargetUrl);
			logoutSuccessHandler.setTargetUrlParameter(targetUrlParameter);

			LoginFailureHandler loginFailureHandler = new LoginFailureHandler();
			loginFailureHandler.setDefaultFailureUrl(defaultTargetUrl);

			AccessFailureHandler accessFailureHandler = new AccessFailureHandler(loginUrl, errorPageUrl);
			accessFailureHandler.setRedirect(config.getBoolean("security.accessFailureRedirect"));

//			BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new BasicAuthenticationEntryPoint();
//			BasicAuthenticationFilter basicAuthenticationFilter = new BasicAuthenticationFilter(authenticationManager, unauthorizedAccessHandler);

			http
					.addFilterAt(this.filterSecurityInterceptor, FilterSecurityInterceptor.class)
//					.addFilterAt(basicAuthenticationFilter, BasicAuthenticationFilter.class)
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