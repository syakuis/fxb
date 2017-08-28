package org.fxb.core.properties.beans.factory;

import org.fxb.core.properties.Properties;
import org.fxb.core.properties.PropertiesLoader;
import org.fxb.core.properties.SpringPropertiesLoader;
import org.fxb.core.properties.exceptions.PropertiesException;

/**
 * properties 로드하여 spring bean 으로 생성한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 29.
 */
public class SpringPropertiesFactoryBean extends AbstractPropertiesFactoryBean {
	@Override
	public Properties getObject() throws PropertiesException {
		PropertiesLoader propertiesLoader = new SpringPropertiesLoader(servletContext, environment, locations);
		if (this.fileEncoding != null) {
			propertiesLoader.setFileEncoding(this.fileEncoding);
		}
		return propertiesLoader.getProperties();
	}
}
