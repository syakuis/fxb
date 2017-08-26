package org.fxb.core.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PropertiesConfiguration.class)
@WebAppConfiguration
@ActiveProfiles(value = "test")
public class PropertiesFactoryBeanTest {
	private final Logger logger = LoggerFactory.getLogger(PropertiesFactoryBeanTest.class);

	@Autowired
	Properties properties;

	@Test
	public void test() {
		properties.getKeys().forEachRemaining(key -> logger.debug("{} = {}", key, properties.getString(key)));
	}
}
