package org.fxb.core.properties;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.BuilderParameters;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
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

		BuilderParameters[] builders = Arrays.stream(resources).map(resource -> {
			logger.debug("><>< Properties Loaded : {}", resource);
			try {
				return params.properties()
						.setEncoding(this.getFileEncoding())
						.setFile(resource.getFile());
			} catch (IOException e) {
				logger.debug("><>< Properties Loaded : {}", e.getMessage());
			}

			return null;
		}).filter(s -> s != null).toArray(s -> new BuilderParameters[s]);

		try {
			Configuration configuration = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
					.configure(builders).getConfiguration();

			return new CommonsConfigurationProperties(configuration, this.delimiter);
		} catch (ConfigurationException e) {
			throw new PropertiesException(e);
		}
	}


}
