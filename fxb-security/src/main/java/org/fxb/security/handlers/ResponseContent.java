package org.fxb.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fxb.commons.web.servlet.handlers.Done;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 6. 18.
 */
class ResponseContent<T> {
	private static final Logger logger = LoggerFactory.getLogger(ResponseContent.class);

	ResponseContent(HttpServletResponse response, Done<T> success) {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = null;

		try {
			writer = response.getWriter();
			writer.print(new ObjectMapper().writeValueAsString(success));
			writer.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
