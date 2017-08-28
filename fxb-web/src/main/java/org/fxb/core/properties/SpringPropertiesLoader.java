package org.fxb.core.properties;

import org.fxb.core.properties.exceptions.PropertiesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import javax.servlet.ServletContext;
import java.io.IOException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 26.
 */
public class SpringPropertiesLoader extends AbstractPropertiesLoader {
	// todo
	private final Logger logger = LoggerFactory.getLogger(SpringPropertiesLoader.class);

	public SpringPropertiesLoader(ServletContext servletContext, Environment environment, String[] locations) {
		super(servletContext, environment, locations);
	}

	@Override
	public Properties getProperties() throws PropertiesException {
		Resource[] resources =
				this.getLocationResources(this.getLocationProfileFormat());

		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();

		try {
			propertiesFactoryBean.setLocations(resources);
			propertiesFactoryBean.setIgnoreResourceNotFound(true); // 프로퍼티 파일이 없는 경우 무시한다.
			propertiesFactoryBean.setFileEncoding(this.getFileEncoding());
			propertiesFactoryBean.setLocalOverride(true); // 중복의 프로퍼티인 경우 나중에 프로퍼티를 사용한다.
			propertiesFactoryBean.setSingleton(true); // 싱글톤 여부 기본값 true

			propertiesFactoryBean.afterPropertiesSet();

			return new SpringPropertiesProperties(propertiesFactoryBean.getObject(), delimiter);
		} catch (IOException e) {
			logger.debug("><>< Properties Loaded : {}", e.getMessage());
		}


		return null;
	}
}
