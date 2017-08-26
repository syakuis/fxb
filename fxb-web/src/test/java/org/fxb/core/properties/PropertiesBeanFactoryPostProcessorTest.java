package org.fxb.core.properties;

import org.fxb.core.properties.beans.PropertiesBeanFactoryPostProcessor;
import org.fxb.core.properties.beans.factory.CommonsConfigurationFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class PropertiesBeanFactoryPostProcessorTest {
	private final Logger logger = LoggerFactory.getLogger(PropertiesBeanFactoryPostProcessorTest.class);


	@Configuration
	static class PropertiesConfiguration extends PropertiesConfigurationConstant {

		@Bean
		static PropertiesBeanFactoryPostProcessor commonsConfigurationPostProcessor() {
			return new PropertiesBeanFactoryPostProcessor(
					"properties",
					CommonsConfigurationFactoryBean.class,
					locations
			);
		}
	}

	@Autowired
	Properties properties;

	@Test
	public void test() {
		properties.getKeys().forEachRemaining(key -> logger.debug("{} = {}", key, properties.getString(key)));
	}
}
