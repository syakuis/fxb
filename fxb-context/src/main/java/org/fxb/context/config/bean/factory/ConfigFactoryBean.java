package org.fxb.context.config.bean.factory;

import java.util.Properties;
import lombok.Setter;
import org.fxb.config.Config;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 3.
 */
public class ConfigFactoryBean implements FactoryBean<Config>, InitializingBean {
	private final Properties properties;
	@Setter
	private boolean singleton = true;
	private Config singletonInstance;

	public ConfigFactoryBean(Properties properties) {
		Assert.notNull(properties, "The argument must not be null");
		this.properties = properties;
	}

	private Config newInstance() {
		return new Config(this.properties);
	}

	@Override
	public void afterPropertiesSet() {
		if (this.singleton) {
			this.singletonInstance = this.newInstance();
		}
	}

	@Override
	public Config getObject() {
		if (this.singleton) {
			return this.singletonInstance;
		}
		return this.newInstance();
	}

	@Override
	public Class<Config> getObjectType() {
		return Config.class;
	}

	@Override
	public boolean isSingleton() {
		return this.singleton;
	}
}
