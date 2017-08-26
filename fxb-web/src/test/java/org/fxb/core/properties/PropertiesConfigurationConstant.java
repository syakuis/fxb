package org.fxb.core.properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 26.
 */
public class PropertiesConfigurationConstant {
	static final String[] locations = new String[]{
			"classpath:org/fxb/config/fxb.properties",
			"classpath:fxb.properties",
			"classpath:fxb-%s.properties"
	};

	static final String fileEcoding = null;
}
