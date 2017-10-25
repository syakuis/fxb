package org.fxb.security.handlers;


import org.fxb.commons.web.http.RequestUtils;
import org.fxb.commons.web.servlet.handlers.Done;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 9. 21.
 */
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

	private String getRedirectUrl(HttpServletRequest request, HttpServletResponse response) {
		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest == null) {
			return determineTargetUrl(request, response);
		}

		String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);

			return determineTargetUrl(request, response);
		}

		clearAuthenticationAttributes(request);
		return savedRequest.getRedirectUrl();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if(RequestUtils.isAjax(request)) {
			String targetUrlParameter = getTargetUrlParameter();
			String redirectUrl = getRedirectUrl(request, response);

			Done<Map<String, Object>> success = new Done<>();

			Map<String, Object> data = new HashMap<>();

			data.put("principal", authentication.getPrincipal());
			data.put("details", authentication.getDetails());
			data.put("credentials", authentication.getCredentials());
			if (targetUrlParameter != null) {
				data.put(getTargetUrlParameter(), redirectUrl);
			}
			success.setData(data);

			new ResponseContent<>(response, success);
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}