package org.fxb.boot;

import org.fxb.boot.beans.factory.ConfigurePropertiesFactoryBean;
import org.fxb.boot.beans.factory.config.ConfigurePropertiesConfigurer;
import org.fxb.config.Fxb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ConfigurePropertiesConfigurer.class)
public class ConfigPropertiesTest {
	private static final Logger logger = LoggerFactory.getLogger(ConfigPropertiesTest.class);

	private Fxb fxb;

	@Autowired
	public void setFxb(Fxb fxb) {
		this.fxb = fxb;
	}

	@Test
	public void config() {
		logger.debug(fxb.toString());
	}

}
