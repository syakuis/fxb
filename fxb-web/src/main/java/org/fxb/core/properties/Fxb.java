package org.fxb.core.properties;

import lombok.Getter;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 30.
 */
@Getter
public final class Fxb {
	private final String version = "1.0.0";
	private final Properties properties;
	private final TimeZone timeZone;
	private final Locale locale;

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

	public Fxb(Properties properties) {
		this.properties = properties;

		// todo timeZone , locale 변경할 수 있게 수정.
		this.timeZone = TimeZone.getDefault();
		this.locale = Locale.getDefault();

		profile = properties.getString("profile");
		profiles = properties.getStringArray("profiles");

		charset = properties.getString("charset");
		cacheSeconds = properties.getInt("cacheSeconds");

		rootAbsolutePath = properties.getString("rootAbsolutePath");

		resourceHandlers = properties.getStringArray("resourceHandlers");
		resourceLocations = properties.getStringArray("resourceLocations");

		viewResolverCache = properties.getBoolean("viewResolverCache");

		freemarkerTemplateLoaderPath = new String[]{
				"classpath:/META-INF/resources/WEB-INF/views/",
				properties.getString("freemarkerTemplateLoaderPath")
		};
		freemarkerExposeSpringMacroHelpers = properties.getBoolean("freemarkerExposeSpringMacroHelpers");
	}
}
