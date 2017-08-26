package org.fxb.boot;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.fxb.boot.properties.InitializingConfigureProperties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles(value = "test")
public class InitializingConfigurePropertiesTest {
	private final Logger logger = LoggerFactory.getLogger(InitializingConfigurePropertiesTest.class);

	@Configuration
	static class BootstrapTest {
	}

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private Environment environment;

	private InitializingConfigureProperties configureProperties;

	@Before
	public void before() {
		Assert.assertNotNull("is null", servletContext);
		Assert.assertNotNull("is null", environment);

		String[] locations = new String[] {
				"classpath:fxb-%s.properties",
				"classpath:fxb.properties",
				"classpath:org/fxb/config/fxb.properties",
		};

		this.configureProperties =
				new InitializingConfigureProperties(servletContext, environment, locations);
	}

	@Test
	public void 설정_초기화_테스트() {
		String profile = "test";
		String encoding = "EUC-KR";
		this.configureProperties.setFileEncoding(encoding);

		Assert.assertEquals(this.configureProperties.getFileEncoding(), encoding);
		Assert.assertTrue(
				String.join(",", this.configureProperties.getLocationProfileFormat()).indexOf("%s") == -1);
		Assert.assertArrayEquals(this.environment.getActiveProfiles(), this.configureProperties.getProfiles());
		Assert.assertEquals(this.configureProperties.getProfile(), profile);
	}

	@Test
	public void 설정_로드_테스트() throws IOException {
		Resource[] resources =
				this.configureProperties.getLocationResources(this.configureProperties.getLocationProfileFormat());
		Arrays.stream(resources).forEach(resource -> logger.debug(resource.toString()));

		Assert.assertNotNull(resources);
	}

	@Test
	public void 아파치_설정_테스트() throws ConfigurationException {
		this.configureProperties.setFileEncoding("EUC-KR");

		Resource[] resources =
				this.configureProperties.getLocationResources(this.configureProperties.getLocationProfileFormat());

		Parameters params = new Parameters();
		CompositeConfiguration configuration = new CompositeConfiguration();

		Arrays.stream(resources).forEach(resource -> {
			logger.debug("><>< Properties Loaded : {}", resource);
			try {
				PropertiesBuilderParameters parameter = params.properties()
						.setEncoding(this.configureProperties.getFileEncoding())
//						.setListDelimiterHandler(new DefaultListDelimiterHandler(','))
//						.setListDelimiterHandler(new DisabledListDelimiterHandler())
						.setFile(resource.getFile());
				FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
						new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
						.configure(parameter);

				configuration.addConfiguration(builder.getConfiguration());
			} catch (IOException e) {
				logger.debug("><>< Properties Loaded : {}", e.getMessage());
			} catch (ConfigurationException e) {
				logger.debug("><>< Properties Loaded : {}", e.getMessage());
			}
		});

		configuration.getKeys().forEachRemaining(key -> logger.debug("><>< {} = {}", key, configuration.getString(key)));

		Assert.assertEquals(1000, configuration.getInt("cacheSeconds"));

		String changeTestKey = "title";
		String changeTestValue = "It`s good!!!";

		logger.debug("><>< {} = {}", changeTestKey, configuration.getString(changeTestKey));

		configuration.setProperty(changeTestKey, changeTestValue);

		logger.debug("><>< {} = {}", changeTestKey, configuration.getString(changeTestKey));

		Assert.assertEquals(configuration.getString(changeTestKey), changeTestValue);

//		ImmutableConfiguration immutableConfiguration = ConfigurationUtils.unmodifiableConfiguration(configuration);

		// 리스트는 같은 키의 줄이 모여서 만들어 진다.
		Assert.assertThat(
				Arrays.asList(
						StringUtils.delimitedListToStringArray(configuration.getString("stringArray"), ",")
				),
				is(configuration.getList("string.array"))
		);
	}
}
