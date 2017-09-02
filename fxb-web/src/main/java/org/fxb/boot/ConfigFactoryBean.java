package org.fxb.boot;

import org.fxb.config.Config;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

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
	public Config getObject() throws Exception {
		return new Config(this.properties, TimeZone.getDefault(), Locale.getDefault());
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
