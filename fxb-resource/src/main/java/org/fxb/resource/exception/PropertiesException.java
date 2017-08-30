package org.fxb.resource.exception;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 22.
 */
public class PropertiesException extends Exception {
	private static final long serialVersionUID = 2142794901500449288L;

	public PropertiesException() {
	}

	public PropertiesException(String message) {
		super(message);
	}

	public PropertiesException(Throwable cause) {
		super(cause);
	}

	public PropertiesException(String message, Throwable cause) {
		super(message, cause);
	}
}
