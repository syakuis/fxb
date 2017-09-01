package org.fxb.resource;

import org.fxb.util.io.MessageSourcePathMatchingResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class MessageSourceLoaderTest {
	private final Logger logger = LoggerFactory.getLogger(MessageSourceLoaderTest.class);

	private MessageSourcePathMatchingResource messageSourceMatchingPattern = new MessageSourcePathMatchingResource();

	private String basenamesPattern =
			"classpath*:org/hibernate/validator/message.properties," +
			"classpath*:message.properties," +
			"classpath*:org/fxb/**/message.properties";

	@Configuration
	static class MessageSourceConfiguration {

	}

	@Test
	public void loader() throws IOException {
		String[] resources = messageSourceMatchingPattern.getResources(basenamesPattern);
		logger.debug("{}", resources.length);
		Arrays.stream(resources).forEach(resource -> logger.debug(resource));
	}

	@Test
	public void reloadableResourceBundleMessageSource() throws IOException {
		String[] resources = messageSourceMatchingPattern.getResources(basenamesPattern);
		ReloadableResourceBundleMessageSource messageSourceLoader = new ReloadableResourceBundleMessageSource();
		// 먼저 로드된 프로퍼티가 나중에 로드된 프로퍼티와 동일한 키인 경우 나중에 로드된 프로퍼티는 무시된다.
		messageSourceLoader.setBasenames(resources);

		MessageSourceAccessor messageSource = new MessageSourceAccessor(messageSourceLoader);

		String basenames = basenamesPattern.replace("\\.properties", "");

		String[] resources2 = messageSourceMatchingPattern.getResources(basenames);
		ReloadableResourceBundleMessageSource messageSourceLoader2 = new ReloadableResourceBundleMessageSource();
		// 먼저 로드된 프로퍼티가 나중에 로드된 프로퍼티와 동일한 키인 경우 나중에 로드된 프로퍼티는 무시된다.
		messageSourceLoader.setBasenames(resources2);

		MessageSourceAccessor messageSource2 = new MessageSourceAccessor(messageSourceLoader);

		Assert.assertEquals(messageSource.getMessage("hello"), messageSource2.getMessage("hello"));
		Assert.assertEquals(messageSource.getMessage("name"), messageSource2.getMessage("name"));

	}

	@Test
	public void nullTest() {
		ReloadableResourceBundleMessageSource messageSourceLoader = new ReloadableResourceBundleMessageSource();
		messageSourceLoader.setBasenames(new String[]{ });

		MessageSourceAccessor messageSource = new MessageSourceAccessor(messageSourceLoader);

		messageSource.getMessage("good");
	}
}
