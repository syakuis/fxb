package org.fxb.resource.bean.factory;

import org.fxb.resource.exception.PropertiesException;
import org.fxb.resource.properties.PropertiesLoader;

import java.util.Properties;

/**
 * Properties 를 읽어서 객체를 생성한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 29.
 */

public class PropertiesFactoryBean extends AbstractPropertiesFactoryBean {
	@Override
	public Properties getObject() throws PropertiesException {
		PropertiesLoader propertiesLoader = new PropertiesLoader(servletContext, environment, locations);
		if (this.fileEncoding != null) {
			propertiesLoader.setFileEncoding(this.fileEncoding);
		}
		return propertiesLoader.getProperties();
	}
}
