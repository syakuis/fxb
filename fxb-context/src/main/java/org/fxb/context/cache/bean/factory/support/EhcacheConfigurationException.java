package org.fxb.context.cache.bean.factory.support;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 13.
 */
public class EhcacheConfigurationException extends Exception {
	public EhcacheConfigurationException() {
	}

	public EhcacheConfigurationException(String message) {
		super(message);
	}

	public EhcacheConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EhcacheConfigurationException(Throwable cause) {
		super(cause);
	}

	public EhcacheConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
