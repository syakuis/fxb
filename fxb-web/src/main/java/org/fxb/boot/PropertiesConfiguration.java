package org.fxb.boot;

import org.fxb.resource.bean.factory.PropertiesBeanFactoryPostProcessor;
import org.fxb.resource.bean.factory.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Config Bean 과 Properties Bean 을 생성한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 19.
 */
@Configuration
public class PropertiesConfiguration {
	@Bean
	static PropertiesBeanFactoryPostProcessor propertiesBeanFactoryPostProcessor() {
		String[] locations = new String[]{
				"classpath:org/fxb/config/default.properties",
				"classpath:org/fxb/config/config.properties",
				"config.properties",
				"config-%s.properties"
		};

		return new PropertiesBeanFactoryPostProcessor("properties", PropertiesFactoryBean.class, locations);
	}

	@Bean
	@DependsOn("properties")
	static ConfigBeanFactoryPostProcessor configBeanFactoryPostProcessor() {
		return new ConfigBeanFactoryPostProcessor();
	}
}
