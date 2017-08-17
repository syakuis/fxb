package org.fxb.boot.properties;


import org.apache.commons.lang3.StringUtils;

import org.fxb.boot.enums.Profile;
import org.fxb.config.Fxb;
import org.fxb.util.io.ResourcesMatchingPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * salt.properties to instance.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 18.
 */
public class InitializingConfigureProperties {
	private static final Logger logger = LoggerFactory.getLogger(InitializingConfigureProperties.class);
	private static final String CHARSET_NAME = "charset";

	private final ServletContext servletContext;
	private final Environment environment;
	private final String[] locations;
	private String fileEncoding = Charset.defaultCharset().name();
	private Fxb fxb;

	public InitializingConfigureProperties(ServletContext servletContext, Environment environment, String[] locations) {
		this.servletContext = servletContext;
		this.environment = environment;
		this.locations = locations;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	/**
	 * 프레임워크에서 사용될 언어셋을 최종 선정한다.
	 * default = utf-8 , properties , system properties 순으로 최종적으로 설정된다.
	 */
	private void setFileEncoding() {
		if (fileEncoding == null && !"".equals(fileEncoding)) {
			fileEncoding = "UTF-8";
		}

		String charset = environment.getProperty(CHARSET_NAME);
		if (charset != null && !"".equals(charset)) {
			fileEncoding = charset;
		}
	}

	public void afterPostProcessor() {

		setFileEncoding();

		String[] profiles = environment.getActiveProfiles();
		if (profiles != null && profiles.length == 0) {
			profiles = environment.getDefaultProfiles();
		}

		String profile = getProfile();

		String[] configLocations = new String[locations.length];

		for(int i = 0; i < locations.length; i++) {
			String location = String.format(locations[i], profile);
			configLocations[i] = location;
		}

		try {
			ResourcesMatchingPattern resourcesMatchingPattern = new ResourcesMatchingPattern();
			Resource[] resources = resourcesMatchingPattern.getResources(configLocations);

			PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
			propertiesFactoryBean.setLocations(resources);
			propertiesFactoryBean.setIgnoreResourceNotFound(true); // 프로퍼티 파일이 없는 경우 무시한다.
			propertiesFactoryBean.setFileEncoding(fileEncoding);
			propertiesFactoryBean.setLocalOverride(true); // 중복의 프로퍼티인 경우 나중에 프로퍼티를 사용한다.
			propertiesFactoryBean.setSingleton(true); // 싱글톤 여부 기본값 true

			propertiesFactoryBean.afterPropertiesSet();

			Properties properties = propertiesFactoryBean.getObject();

			properties.setProperty("profiles", StringUtils.join(profiles, ","));
			properties.setProperty("profile", profile);
			properties.setProperty("charset", fileEncoding);
			properties.setProperty("rootAbsolutePath", servletContext.getRealPath("/"));

			TimeZone timeZone = TimeZone.getDefault();
			Locale locale = Locale.getDefault();

			this.fxb = new Fxb(properties, timeZone, locale);
			intro(properties);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Fxb getConfig() {
		return fxb;
	}

	private String getProfile() {
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
	 * http://www.network-science.de/ascii/
	 * standard type smslant slant
	 */
	private void intro(Properties properties) {
		StringBuilder print = new StringBuilder();

		print.append("\n_________________________________________________________________________________\n");

		try (Stream<String> stream = Files.lines(
				Paths.get(new ClassPathResource("org/fxb/config/intro").getURI()))
		) {
			stream.forEach(intro -> print.append(intro).append("\n"));
		} catch (IOException ioe) {
			logger.debug("file not found.");
		}

		print.append("                                                                  version ")
				.append(fxb.getVersion())
				.append("  \n")
				.append("                                                                                 \n")
				.append("                                                                                 \n")
				.append("                           Frontend X Backend (Fxb) by 52572 49437 44512         \n")
				.append("                                                                                 \n")
				.append("_________________________________________________________________________________\n\n")
				.append("* Locale: ").append(fxb.getLocale().getLanguage()).append("\n")
				.append("* TimeZone: ").append(fxb.getTimeZone().getID()).append("\n")
				.append("* Date: ").append(new Date()).append("\n")
				.append("* Encoding: ").append(fxb.getCharset()).append("\n")
				.append("* Profile: ").append(fxb.getProfile()).append("\n")
				.append("* profiles: ").append(properties.getProperty("profiles")).append("\n")
				.append("* rootAbsolutePath: ").append(properties.getProperty("rootAbsolutePath")).append("\n")
				.append("_________________________________________________________________________________\n\n");

		logger.warn(print.toString());

		if (logger.isDebugEnabled()) {
			Iterator<String> iterator = properties.stringPropertyNames().iterator();

			while (iterator.hasNext()) {
				String name = iterator.next();

				logger.debug("{} : {}", name, properties.getProperty(name));
			}
		}

	}
}
