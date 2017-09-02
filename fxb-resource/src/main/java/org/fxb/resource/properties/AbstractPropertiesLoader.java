package org.fxb.resource.properties;


import org.apache.commons.lang3.StringUtils;
import org.fxb.core.boot.enums.Profile;
import org.fxb.resource.exception.PropertiesException;
import org.fxb.util.io.MultiplePathMatchingResourcePatternResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 22.
 */
public abstract class AbstractPropertiesLoader {
	private static final Logger logger = LoggerFactory.getLogger(AbstractPropertiesLoader.class);
	private static final String CHARSET_NAME = "charset";

	protected final ServletContext servletContext;
	protected final Environment environment;
	private final String[] locations;
	private String fileEncoding = Charset.defaultCharset().name();
	protected String delimiter = ",";

	/**
	 *
	 * @param servletContext
	 * @param environment
	 * @param locations
	 */
	public AbstractPropertiesLoader(ServletContext servletContext, Environment environment, String[] locations) {
		this.servletContext = servletContext;
		this.environment = environment;
		this.locations = locations;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * 프레임워크에서 사용될 언어셋을 최종 선정한다.
	 * default = utf-8 , properties , system properties 순으로 최종적으로 설정된다.
	 */
	protected String getFileEncoding() {
		Assert.notNull(environment, "The class must not be null. (environment)");

		String encoding = StringUtils.defaultString(
				environment.getProperty(CHARSET_NAME),
				StringUtils.defaultString(new String(this.fileEncoding), "UTF-8")
		);

		return encoding;
	}

	/**
	 * 운영환경을 가져온다. 운영환경 기본값은 4가지로 정의한다.
	 * DEFAULT , DEV , TEST , PROD
	 * @return
	 */
	protected String getProfile() {
		Assert.notNull(environment, "The class must not be null. (environment)");

		if (environment.acceptsProfiles(Profile.DEV.getValue())) {
			return Profile.DEV.getValue();
		} else if (environment.acceptsProfiles(Profile.TEST.getValue())) {
			return Profile.TEST.getValue();
		} else if (environment.acceptsProfiles(Profile.PROD.getValue())) {
			return Profile.PROD.getValue();
		} else {
			return Profile.DEFAULT.getValue();
		}
	}

	/**
	 * environment profiles 정보를 가져온다.
	 * @return
	 */
	protected String[] getProfiles() {
		Assert.notNull(environment, "The class must not be null. (environment)");

		String[] profiles = environment.getActiveProfiles();
		if (profiles != null && profiles.length == 0) {
			return environment.getDefaultProfiles();
		}

		return profiles;
	}

	/**
	 * locations 값에 String.format 형식인 경우 profile 대입한다.
	 * @return
	 */
	protected String[] getLocationProfileFormat() {
		Assert.notNull(locations, "The class must not be null. (locations)");
		return Arrays.stream(locations).map(location -> String.format(location, this.getProfile())).toArray(String[]::new);
	}

	/**
	 * Locations 의 프로퍼티를 읽어 Resouece 반환한다.
	 *
	 * "classpath:org/fxb/config/fxb.properties",
	 * "classpath:fxb.properties",
	 * "classpath:fxb-%s.properties"
	 * @return
	 */
	protected Resource[] getLocationResources(String[] configLocations) {
		Assert.notEmpty(configLocations, "The array must contain elements. (configLocations)");

		try {
			MultiplePathMatchingResourcePatternResolver matchingPattern = new MultiplePathMatchingResourcePatternResolver();
			return matchingPattern.getResources(configLocations);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}

	public abstract Properties getProperties() throws PropertiesException;
}
