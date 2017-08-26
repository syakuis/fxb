package org.fxb.core.properties;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.fxb.core.properties.exceptions.PropertiesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 22.
 */
public class CommonsConfigurationLoader extends AbstractPropertiesLoader {
	// todo DI 변경하기.
	private final Logger logger = LoggerFactory.getLogger(CommonsConfigurationLoader.class);

	public CommonsConfigurationLoader(ServletContext servletContext, Environment environment, String[] locations) {
		super(servletContext, environment, locations);
	}

	@Override
	public Properties getProperties() throws PropertiesException {
		Resource[] resources =
				this.getLocationResources(this.getLocationProfileFormat());

		Parameters params = new Parameters();
		CompositeConfiguration configuration = new CompositeConfiguration();

		Arrays.stream(resources).forEach(resource -> {
			try {
				PropertiesBuilderParameters parameter = params.properties()
						.setEncoding(this.getFileEncoding())
//						.setListDelimiterHandler(new DefaultListDelimiterHandler(','))
//						.setListDelimiterHandler(new DisabledListDelimiterHandler())
						.setFile(resource.getFile());
				FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
						new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
								.configure(parameter);

				configuration.addConfiguration(builder.getConfiguration());
				logger.debug("><>< Properties Loaded : {}", resource);
			} catch (IOException e) {
				logger.debug("><>< Properties Loaded : {}", e.getMessage());
			} catch (ConfigurationException e) {
				logger.debug("><>< Properties Loaded : {}", e.getMessage());
			}
		});

		return new CommonsConfigurationProperties(configuration, this.delimiter);
	}


}
