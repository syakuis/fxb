package org.fxb.context.config;

import javax.servlet.ServletContext;
import org.fxb.config.Config;
import org.fxb.context.config.bean.factory.ConfigFactoryBean;
import org.fxb.resource.bean.factory.PropertiesFactoryBean;
import org.fxb.resource.exception.PropertiesException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ServletContextAware;

/**
 * Config Bean 과 Properties Bean 을 생성한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 19.
 */
@Configuration
public class ConfigConfiguration implements EnvironmentAware, ServletContextAware {
	private ServletContext servletContext;
	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Bean
	public Config config() throws PropertiesException {
		String[] locations = new String[]{
				"classpath:org/fxb/config/default.properties",
				"classpath:org/fxb/config/*.config.properties",
				"config.properties",
				"config-%s.properties"
		};

		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setServletContext(servletContext);
		propertiesFactoryBean.setEnvironment(environment);
		propertiesFactoryBean.setLocations(locations);
		propertiesFactoryBean.afterPropertiesSet();

		ConfigFactoryBean configFactoryBean = new ConfigFactoryBean(propertiesFactoryBean.getObject());
		configFactoryBean.afterPropertiesSet();

		return configFactoryBean.getObject();
	}
}
