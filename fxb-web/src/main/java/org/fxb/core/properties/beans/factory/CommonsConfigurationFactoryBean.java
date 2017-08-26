package org.fxb.core.properties.beans.factory;

import org.fxb.core.properties.CommonsConfigurationLoader;
import org.fxb.core.properties.Properties;
import org.fxb.core.properties.PropertiesLoader;
import org.fxb.core.properties.exceptions.PropertiesException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * properties 로드하여 spring bean 으로 생성한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 29.
 */
public class CommonsConfigurationFactoryBean implements FactoryBean<Properties>, EnvironmentAware, ServletContextAware {
	/**
	 * rootAbsolutePath 를 구한기 위함.
	 */
	private ServletContext servletContext;
	/**
	 * The Environment.
	 */
	private Environment environment;
	/**
	 * The File encoding.
	 */
	private String fileEncoding;

	private String[] locations;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	/**
	 * Config properties. 순차적으로 overwrite 된다.
	 * Java OPT -Dspring.profiles.active=test
	 *
	 * @return the properties
	 */
	@Override
	public Properties getObject() throws PropertiesException {
		PropertiesLoader propertiesLoader = new CommonsConfigurationLoader(servletContext, environment, locations);
		if (this.fileEncoding != null) {
			propertiesLoader.setFileEncoding(this.fileEncoding);
		}
		return propertiesLoader.getProperties();
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
