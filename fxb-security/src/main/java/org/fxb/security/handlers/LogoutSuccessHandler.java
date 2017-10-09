package org.fxb.security.handlers;

import org.fxb.commons.web.http.RequestUtils;
import org.fxb.commons.web.servlet.handlers.Done;
import org.fxb.commons.web.servlet.handlers.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 로그아웃 성공 후 호출되는 헨들러
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 5. 30.
 */
public class LogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(LogoutSuccessHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		if(RequestUtils.isAjax(request)) {
			String targetUrl = determineTargetUrl(request, response);

			if (logger.isDebugEnabled()) {
				logger.debug("targetUrl: " + targetUrl);
				logger.debug("targetUrlParameter name: " + getTargetUrlParameter());
				logger.debug("targetUrlParameter value: " + request.getParameter(getTargetUrlParameter()));
			}

			new ResponseContent<>(response, new Done<>(null, false, StatusCode
					.OK, targetUrl));
		} else {
			super.handle(request, response, authentication);
		}

	}
}