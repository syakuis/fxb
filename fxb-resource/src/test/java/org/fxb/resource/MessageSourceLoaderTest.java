package org.fxb.resource;

import org.fxb.util.io.MessageSourcePathMatchingResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
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

	private String parentBasename =
			"classpath*:org/hibernate/validator/message.properties," +
			"classpath*:message.properties," +
			"classpath*:org/fxb/**/message.properties";

	@Configuration
	static class MessageSourceConfiguration {

	}

	@Test
	public void loader() throws IOException {
		String[] basenames = messageSourceMatchingPattern.getResources(parentBasename);
		Arrays.stream(basenames).forEach(basename -> logger.debug(basename));
	}
}
