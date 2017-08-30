package org.fxb.resource.bean.factory;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 26.
 */
public abstract class AbstractPropertiesFactoryBean implements FactoryBean<Properties>, EnvironmentAware, ServletContextAware {
	/**
	 * rootAbsolutePath 를 구한기 위함.
	 */
	ServletContext servletContext;
	/**
	 * The Environment.
	 */
	Environment environment;
	/**
	 * The File encoding.
	 */
	String fileEncoding;

	String[] locations;

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
	public abstract Properties getObject() throws Exception;

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
