package org.fxb.security.handlers;

import org.fxb.commons.web.http.RequestUtils;
import org.fxb.commons.web.servlet.handlers.Done;
import org.fxb.commons.web.servlet.handlers.StatusCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증(Consumer)되지 않은 사용자가 허가되지 않은 페이지에 접근할때 요청되는 헨들러.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 5. 30.
 */
public class UnauthorizedAccessHandler implements AuthenticationEntryPoint {

	private final String loginFormUrl;
	private boolean redirect = true;

	public UnauthorizedAccessHandler(String loginFormUrl) {
		this.loginFormUrl = loginFormUrl;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		if(RequestUtils.isAjax(request)) {
			new ResponseContent(response, new Done(exception.getMessage(), true, StatusCode.Unauthorized));
		} else {
			if (redirect) {
				response.sendRedirect(request.getContextPath() + loginFormUrl);
			} else {
				request.getRequestDispatcher(request.getContextPath() + loginFormUrl).forward(request, response);
			}
		}
	}
}