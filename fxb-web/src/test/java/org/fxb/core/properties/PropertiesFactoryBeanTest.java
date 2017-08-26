package org.fxb.core.properties;

import org.fxb.core.properties.beans.PropertiesBeanFactoryPostProcessor;
import org.fxb.core.properties.beans.factory.CommonsConfigurationFactoryBean;
import org.fxb.core.properties.exceptions.PropertiesException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.ServletContext;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles(value = "test")
public class PropertiesFactoryBeanTest {
	private final Logger logger = LoggerFactory.getLogger(PropertiesFactoryBeanTest.class);

	@Configuration
	static class PropertiesConfiguration extends PropertiesConfigurationConstant {
		@Autowired
		ServletContext servletContext;

		@Autowired
		Environment environment;

		@Bean
		public Properties properties() throws PropertiesException {
			CommonsConfigurationFactoryBean factoryBean = new CommonsConfigurationFactoryBean();
			factoryBean.setServletContext(servletContext);
			factoryBean.setEnvironment(environment);
			factoryBean.setLocations(locations);
			return factoryBean.getObject();
		}
	}

	@Configuration
	static class PropetiesPostProcessorConfiguraion extends PropertiesConfigurationConstant {
		@Bean
		static PropertiesBeanFactoryPostProcessor commonsConfigurationPostProcessor() {
			return new PropertiesBeanFactoryPostProcessor(
					"properties2",
					CommonsConfigurationFactoryBean.class,
					locations
			);
		}
	}

	@Autowired
	Properties properties;

	@Autowired
	Properties properties2;

	@Test
	public void test() {
		Assert.assertNotNull(properties);
		Assert.assertNotNull(properties2);

		properties.getKeys().forEachRemaining(key -> {
			Assert.assertEquals(properties.getString(key), properties2.getString(key));
			logger.debug("{} = {}", key, properties.getString(key));
		});
	}
}
