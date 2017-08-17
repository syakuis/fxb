package org.apache.commons.configuration2;

import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 17.
 */
public class ConfigurationTest {
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationTest.class);

	@Test
	public void getTest() {
		Configurations configs = new Configurations();

		try {
			logger.debug(getClass().getClassLoader().getResource("org/apache/commons/configuration2/test.properties").toString());
			Configuration config = configs.properties("org/apache/commons/configuration2/test.properties");
			Configuration config2 = configs.properties("org/apache/commons/configuration2/test22.properties");

			CompositeConfiguration cc = new CompositeConfiguration();
			cc.addConfiguration(config);
			cc.addConfiguration(config2);

			logger.debug(cc.getString("name"));
			logger.debug(cc.getString("name2"));


		} catch (ConfigurationException e) {

		}

	}
}
