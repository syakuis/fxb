package org.fxb.config;

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

	@Autowired
	private Config config;

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPaths(
				config.getString("freeMarker.templateLoaderPath")
		);
		configurer.setDefaultEncoding(config.getCharset());

		Properties properties = new Properties();
		properties.setProperty("cache_storage", "freemarker.cache.NullCacheStorage");
		properties.setProperty("auto_import", "spring.ftl as spring");
		properties.setProperty("number_format", "0.####");
		configurer.setFreemarkerSettings(properties);

		return configurer;
	}
}
