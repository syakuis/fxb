package org.fxb.commons.web.http;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 6. 18.
 */
public class RequestUtils {
	private RequestUtils() {}

	public static String getPathQueryString(HttpServletRequest request) {
		String servlet_path = request.getServletPath();
		String query_string = request.getQueryString();
		query_string = ( StringUtils.isEmpty(query_string) ) ? "" : "?" + query_string;
		String servlet_url = servlet_path + query_string;

		return servlet_url;
	}

	public static boolean isAjax(HttpServletRequest request) {
		return StringUtils.isNotEmpty(request.getHeader("X-Requested-With"));
	}
}
