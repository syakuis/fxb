package org.fxb.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 10. 23.
 */
public class SecuritySupportConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(SecuritySupportConfiguration.class);

	@Autowired
	private FilterSecurityInterceptor filterSecurityInterceptor;

	@Bean
	public WebInvocationPrivilegeEvaluator privilegeEvaluator() {
		return new DefaultWebInvocationPrivilegeEvaluator(filterSecurityInterceptor);
	}
}
