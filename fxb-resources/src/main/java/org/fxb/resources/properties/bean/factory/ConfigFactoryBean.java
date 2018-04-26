package org.fxb.resources.properties.bean.factory;


import java.util.Properties;
import org.fxb.resources.properties.Config;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 3.
 */
public class ConfigFactoryBean extends AbstractFactoryBean<Config> {
  private final Properties properties;

  public ConfigFactoryBean(Properties properties) {
    if (properties == null) {
      throw new IllegalArgumentException("The argument must not be null");
    }
    this.properties = properties;
  }

  @Override
  public Class<Config> getObjectType() {
    return Config.class;
  }

  @Override
  protected Config createInstance() {
    return new Config(this.properties);
  }
}