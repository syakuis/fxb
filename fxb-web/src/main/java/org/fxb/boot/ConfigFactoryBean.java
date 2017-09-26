package org.fxb.boot;

import org.fxb.config.Config;
import org.springframework.beans.factory.FactoryBean;

import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 3.
 */
public class ConfigFactoryBean implements FactoryBean<Config> {
	private final Properties properties;

	public ConfigFactoryBean(Properties properties) {
		this.properties = properties;
	}

	@Override
	public Config getObject() {
		return new Config(this.properties);
	}

	@Override
	public Class<Config> getObjectType() {
		return Config.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
