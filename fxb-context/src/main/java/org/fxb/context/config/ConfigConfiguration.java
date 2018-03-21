package org.fxb.context.config;

import java.io.IOException;
import java.util.Properties;
import org.fxb.context.Config;
import org.fxb.context.config.bean.factory.ConfigFactoryBean;
import org.fxb.resources.properties.bean.factory.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config Bean 과 Properties Bean 을 생성한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 19.
 */
@Configuration
public class ConfigConfiguration {

  @Bean
  public PropertiesFactoryBean configPropertiesFactoryBean() {
    String[] locations = new String[]{
        "classpath:org/fxb/config/default.properties",
        "classpath:org/fxb/config/*.config.properties",
        "config.properties",
        "config-[profile].properties"
    };

    PropertiesFactoryBean bean = new PropertiesFactoryBean();
    bean.setLocations(locations);

    return bean;
  }

  @Bean
  public Config config() throws Exception {
    Properties properties = configPropertiesFactoryBean().getObject();
    ConfigFactoryBean configFactoryBean =
        new ConfigFactoryBean(properties);
    return configFactoryBean.getObject();
  }
}
