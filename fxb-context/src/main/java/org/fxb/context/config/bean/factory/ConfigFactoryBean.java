package org.fxb.context.config.bean.factory;

import java.util.Properties;
import org.fxb.context.Config;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 3.
 */
public class ConfigFactoryBean implements FactoryBean<Config> {
  private final Properties properties;
  private boolean singleton = true;

  public ConfigFactoryBean(Properties properties) {
    Assert.notNull(properties, "The argument must not be null");
    this.properties = properties;
  }

  private Config newInstance() {
    return new Config(this.properties);
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
    return this.singleton;
  }
}
