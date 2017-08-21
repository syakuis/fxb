package org.fxb.core.properties.beans.factory;

import org.fxb.core.properties.Fxb;
import org.fxb.core.properties.PropertiesLoader;
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
public class PropertiesFactoryBean implements FactoryBean<Fxb>, EnvironmentAware, ServletContextAware {
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
	
	private final PropertiesLoader propertiesLoader;

	public PropertiesFactoryBean(PropertiesLoader propertiesLoader) {
		this.propertiesLoader = propertiesLoader;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * Config properties. 순차적으로 overwrite 된다.
	 * Java OPT -Dspring.profiles.active=test
	 *
	 * @return the properties
	 */
	@Override
	public Fxb getObject() {

		String[] locations = new String[]{
				"classpath:org/fxb/config/fxb.properties",
				"classpath:fxb.properties",
				"classpath:fxb-%s.properties"
		};

		return null;
	}

	@Override
	public Class<Fxb> getObjectType() {
		return Fxb.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
