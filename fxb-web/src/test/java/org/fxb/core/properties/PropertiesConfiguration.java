package org.fxb.core.properties;

import org.fxb.core.properties.beans.factory.CommonsConfigurationFactoryBean;
import org.fxb.core.properties.exceptions.PropertiesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.ServletContext;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 25.
 */
@Configuration
public class PropertiesConfiguration {

	@Autowired
	ServletContext servletContext;

	@Autowired
	Environment environment;

	String[] locations = new String[]{
			"classpath:org/fxb/config/fxb.properties",
			"classpath:fxb.properties",
			"classpath:fxb-%s.properties"
	};

	@Bean
	public Properties properties() throws PropertiesException {
		CommonsConfigurationFactoryBean factoryBean = new CommonsConfigurationFactoryBean();
		factoryBean.setServletContext(servletContext);
		factoryBean.setEnvironment(environment);
		factoryBean.setLocations(locations);
		return factoryBean.getObject();
	}
}
