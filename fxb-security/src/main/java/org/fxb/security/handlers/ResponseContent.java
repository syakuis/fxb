package org.fxb.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fxb.commons.web.servlet.handlers.Done;
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
	ResponseContent(HttpServletResponse response, Done<T> success) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		out.print(objectMapper.writeValueAsString(success));
		out.flush();
		out.close();
	}
}
