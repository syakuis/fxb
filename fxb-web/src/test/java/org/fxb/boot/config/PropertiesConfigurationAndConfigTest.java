package org.fxb.boot.config;

import org.fxb.boot.PropertiesConfiguration;
import org.fxb.config.Config;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * test 환경으로 구동하여 Properties 로드하고 Config 클래스를 테스트한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PropertiesConfiguration.class)
@WebAppConfiguration
@ActiveProfiles("test")
@TestPropertySource("/config-test.properties")
public class PropertiesConfigurationAndConfigTest {
	private final Logger logger = LoggerFactory.getLogger(PropertiesConfigurationAndConfigTest.class);

	@Autowired
	Config config;

	@Autowired
	private Environment env;

	private String[] getStringArray(String key) {
		return StringUtils.delimitedListToStringArray(env.getProperty(key), ",");
	}

	private List<String> getList(String key) {
		return Arrays.asList(getStringArray(key));
	}

	/**
	 * Config 메서드들을 테스트한다.
	 */
	@Test
	public void configMethodTest() {
		/*
		 test.string=abc
		 test.stringArray=a,b,c
		 test.boolean=true
		 test.list=a ,b b,c
		 test.number=1
		 */

		String[] stringArray = config.getArray("test.list");
		List<String> stringList = config.getList("test.stringArray");

		Assert.assertEquals(config.getString("test.string"), env.getProperty("test.string"));
		Assert.assertArrayEquals(config.getStringArray("test.stringArray"), this.getStringArray("test.stringArray"));
		Assert.assertArrayEquals(stringArray, new String[]{ "a", "b b", "c" });
		Assert.assertThat(stringList, is(getList("test.stringArray")));
		Assert.assertTrue(config.getBoolean("test.boolean"));
		Assert.assertEquals(config.getLong("test.number"), 1L);
		Assert.assertEquals(config.getInt("test.number"), 1);
	}
}
