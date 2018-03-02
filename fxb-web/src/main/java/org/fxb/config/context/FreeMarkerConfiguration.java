package org.fxb.config.context;

import org.apache.commons.lang3.StringUtils;
import org.fxb.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 13.
 */
@Configuration
public class FreeMarkerConfiguration {
	private final Logger logger = LoggerFactory.getLogger(FreeMarkerConfiguration.class);

	private final String prefix = "freeMarker.props.";

	@Autowired
	private Config config;

	@Bean
	public FreeMarkerConfigurer freemarkerConfig() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

		StringBuilder builder = new StringBuilder(config.getString("default.freeMarker.templateLoaderPath"));
		String templateLoaderPath = config.getString("freeMarker.templateLoaderPath");

		if (StringUtils.isNotEmpty(templateLoaderPath)) {
			builder.append(",").append(templateLoaderPath);
		}

		configurer.setTemplateLoaderPaths(
				StringUtils.split(builder.toString(), ",")
		);
		configurer.setDefaultEncoding(config.getCharset());

		Properties properties = config.getProperties(prefix);
		if (properties != null) {
			configurer.setFreemarkerSettings(properties);
		}

		return configurer;
	}
}
