package org.fxb.config;

import lombok.Getter;
import org.fxb.resource.properties.AbstractProperties;

import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

/**
 * salt.properties to Config.class
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 30.
 */

@Getter
public final class Config extends AbstractProperties {
	private final TimeZone timeZone;
	private final Locale locale;

	private final String version = "1.0.0";
	private String profile;
	private String[] profiles;
	private String charset;

	private String rootAbsolutePath;

	private int cacheSeconds;
	private String[] resourceHandlers;
	private String[] resourceLocations;

	public Config(Properties properties, TimeZone timeZone, Locale locale) {
		super(properties);
		this.timeZone = timeZone;
		this.locale = locale;

		this.binding();
	}

	public void binding() {
		profile = getString("profile");
		profiles = getArray("profiles");

		charset = getString("charset");
		cacheSeconds = getInt("cacheSeconds");

		rootAbsolutePath = getString("rootAbsolutePath");

		resourceHandlers = getArray("resourceHandlers");
		resourceLocations = getArray("resourceLocations");
	}
}
