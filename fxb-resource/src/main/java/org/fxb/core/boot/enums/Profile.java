package org.fxb.core.boot.enums;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 9. 7.
 */
public enum Profile {
	DEFAULT("default"), DEV("dev"), TEST("test"), PROD("prod");

	private String value;

	Profile(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}