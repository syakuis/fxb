package org.fxb.boot;

import org.fxb.resource.bean.factory.PropertiesBeanFactoryPostProcessor;
import org.fxb.resource.bean.factory.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;

import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 13.
 */
@Configuration
@ComponentScan(
		basePackages = "org.fxb.config",
		useDefaultFilters = false,
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class Bootstrapping {

	@Bean
	static PropertiesBeanFactoryPostProcessor propertiesBeanFactoryPostProcessor() {
		String[] locations = new String[]{
				"classpath:org/fxb/config/config.properties",
				"classpath:config-%s.properties"
		};

		return new PropertiesBeanFactoryPostProcessor("properties", PropertiesFactoryBean.class, locations);
	}

	@Bean
	static ConfigBeanFactoryPostProcessor configBeanFactoryPostProcessor() {
		return new ConfigBeanFactoryPostProcessor();
	}
}
