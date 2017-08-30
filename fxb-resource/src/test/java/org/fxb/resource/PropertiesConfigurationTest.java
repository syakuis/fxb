package org.fxb.resource;

import org.fxb.resource.bean.factory.PropertiesBeanFactoryPostProcessor;
import org.fxb.resource.bean.factory.PropertiesFactoryBean;
import org.fxb.resource.exception.PropertiesException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Properties 를 읽어서 객체로 생성하는 테스트를 진행한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class PropertiesConfigurationTest {
	private final Logger logger = LoggerFactory.getLogger(PropertiesConfigurationTest.class);

	static final String[] locations = new String[]{
			"classpath:org/fxb/config/fxb.properties",
			"classpath:fxb.properties",
			"classpath:fxb-%s.properties"
	};

	static final String fileEcoding = null;

	@Configuration
	static class PropertiesConfiguration {
		@Autowired
		ServletContext servletContext;

		@Autowired
		Environment environment;

		@Bean
		public Properties properties() throws PropertiesException {
			PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
			factoryBean.setServletContext(servletContext);
			factoryBean.setEnvironment(environment);
			factoryBean.setLocations(locations);
			return factoryBean.getObject();
		}
	}

	@Configuration
	static class PropetiesPostProcessorConfiguraion {
		@Bean
		static PropertiesBeanFactoryPostProcessor commonsConfigurationPostProcessor() {
			return new PropertiesBeanFactoryPostProcessor(
					"properties2",
					PropertiesFactoryBean.class,
					locations
			);
		}
	}

	@Autowired
	Properties properties;

	@Autowired
	Properties properties2;

	@Value("#{properties['cacheSeconds']}")
	int cacheSeconds;

	@Test
	public void propertiesFactoryBeanTest() {
		Assert.assertNotNull(properties);
		Assert.assertNotNull(properties2);
		Assert.assertEquals(cacheSeconds, 1000);

		Enumeration<Object> enumeration = properties.keys();
		List<String> result = new ArrayList<>();
		while(enumeration.hasMoreElements()) {
			result.add((String) enumeration.nextElement());
		}

		result.stream().forEach(key -> {
			Assert.assertEquals(properties.getProperty(key), properties2.getProperty(key));
			logger.debug("{} = {}\n{} = {}", key, properties.getProperty(key), properties2.getProperty(key));
		});
	}
}
