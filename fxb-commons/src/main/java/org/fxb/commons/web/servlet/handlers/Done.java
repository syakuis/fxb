package org.fxb.commons.web.servlet.handlers;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Controller 처리가 완료되었을 때 응답하기 위한 클래스
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 12. 16.
 */
public class Done<T> {
	@Getter
	private final String message;
	@Getter
	private final boolean error;
	@Getter
	private final Date date = new Date();
	@Getter
	private final StatusCode statusCode;
	@Getter @Setter
	private T data;

	public Done() {
		this(null, false, StatusCode.OK);
	}

	public Done(String message) {
		this(message, false, StatusCode.OK);
	}

	public Done(boolean error) {
		this(null, error, StatusCode.OK);
	}

	public Done(String message, boolean error) {
		this(message, error, StatusCode.OK);
	}

	public Done(String message, boolean error, StatusCode statusCode) {
		this(message, error, statusCode, null);
	}

	public Done(String message, boolean error, StatusCode statusCode, T data) {
		this.message = message;
		this.error = error;
		this.statusCode = statusCode;
		this.data = data;
	}

	public int getCode() {
		return statusCode.getCode();
	}
}