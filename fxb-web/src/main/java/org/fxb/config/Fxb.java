package org.fxb.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

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
@ToString
public final class Fxb {
	private final Properties properties;
	private final TimeZone timeZone;
	private final Locale locale;

	private final String version = "1.0.0";
	private String profile;
	private String[] profiles;
	private String charset;
	private int cacheSeconds;

	private String rootAbsolutePath;

	private String[] resourceHandlers;
	private String[] resourceLocations;

	private boolean viewResolverCache;

	private String[] freemarkerTemplateLoaderPath;
	private boolean freemarkerExposeSpringMacroHelpers;

	public Fxb(Properties properties, TimeZone timeZone, Locale locale) {
		this.properties = properties;
		this.timeZone = timeZone;
		this.locale = locale;

		profile = properties.getProperty("profile");
		profiles = StringUtils.delimitedListToStringArray(properties.getProperty("profiles"), ",");
		properties.remove("profile");

		charset = properties.getProperty("charset");
		properties.remove("charset");
		cacheSeconds = Integer.parseInt(properties.getProperty("cacheSeconds"));
		properties.remove("cacheSeconds");

		rootAbsolutePath = properties.getProperty("rootAbsolutePath");
		properties.remove("rootAbsolutePath");

		resourceHandlers = StringUtils.delimitedListToStringArray(properties.getProperty("resourceHandlers"), ",");
		resourceLocations = StringUtils.delimitedListToStringArray(properties.getProperty("resourceLocations"), ",");
		properties.remove("resourceHandlers");
		properties.remove("resourceLocations");

		viewResolverCache = Boolean.parseBoolean(properties.getProperty("viewResolverCache"));
		properties.remove("viewResolverCache");

		freemarkerTemplateLoaderPath = new String[]{
				"classpath:/META-INF/resources/WEB-INF/views/",
				properties.getProperty("freemarkerTemplateLoaderPath")
		};
		properties.remove("freemarkerTemplateLoaderPath");
		freemarkerExposeSpringMacroHelpers = Boolean.parseBoolean(properties.getProperty("freemarkerExposeSpringMacroHelpers"));
		properties.remove("freemarkerExposeSpringMacroHelpers");
	}

	public String getString(String name) {
		return getString(name, null);
	}

	public String getString(String name, String defaultValue) {
		String value = this.properties.getProperty(name);
		if (!StringUtils.isEmpty(value)) {
			return this.properties.getProperty(name);
		}

		return defaultValue;
	}

	public int getInt(String name) {
		String value = getString(name, "0");
		return Integer.parseInt(value);
	}

	public boolean getBoolean(String name) {
		String value = getString(name, "false");
		return Boolean.parseBoolean(value);
	}
}
