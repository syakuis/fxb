package org.fxb.boot.config;

import org.fxb.boot.PropertiesConfiguration;
import org.fxb.config.MessageSourceConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesConfiguration.class, MessageSourceConfiguration.class })
@WebAppConfiguration
@ActiveProfiles("test")
public class MessageSourceConfigurationTest {

	@Autowired
	MessageSourceAccessor messageSourceAccessor;

	@Test
	public void test() {
		Assert.assertEquals(messageSourceAccessor.getMessage("text.word.first"), "first 변경됨.");
		Assert.assertEquals(messageSourceAccessor.getMessage("test.message"), "Hello World!!!");
	}
}
