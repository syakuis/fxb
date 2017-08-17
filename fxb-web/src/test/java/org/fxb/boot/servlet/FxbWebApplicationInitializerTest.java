package org.fxb.boot.servlet;

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
public class FxbWebApplicationInitializerTest {
	private static final Logger logger = LoggerFactory.getLogger(FxbWebApplicationInitializerTest.class);

	@Test
	public void getClassPathResource() throws IOException {
		// 인수 null 을 사용할 수 없다.
//		logger.debug(new ClassPathResource(null).getURI().getPath());
		// file 이 없는 경우 FileNotFoundException 발생한다.
//		logger.debug(new ClassPathResource("").getURI().getPath());
//		logger.debug(new ClassPathResource("fxb.properties").getURI().getPath());
		logger.debug(new ClassPathResource("org/fxb/config/fxb.properties").getURI().getPath());
	}
}
