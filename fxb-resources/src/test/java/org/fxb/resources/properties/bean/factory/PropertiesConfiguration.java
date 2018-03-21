package org.fxb.resources.properties.bean.factory;

import java.io.IOException;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 3. 19.
 */
@Configuration
public class PropertiesConfiguration {

  @Bean
  public PropertiesFactoryBean propertiesFactoryBean() {
    PropertiesFactoryBean bean = new PropertiesFactoryBean();
    bean.setLocations(
        "classpath:org/fxb/resources/**/first.properties",
        "classpath:org/fxb/resources/*/second.properties"
    );

    return bean;
  }

  @Bean
  public Properties config() throws Exception {
    return propertiesFactoryBean().getObject();
  }

  @Bean
  public PropertiesFactoryBean stringLocationsPropertiesFactoryBean() {
    PropertiesFactoryBean bean = new PropertiesFactoryBean();
    bean.setLocations("classpath:org/fxb/resources/**/first.properties, classpath:org/fxb/resources/*/second.properties");

    return bean;
  }

  @Bean
  public Properties stringPathConfig() throws Exception {
    return stringLocationsPropertiesFactoryBean().getObject();
  }

  @Bean
  public PropertiesFactoryBean profilePropertiesFactoryBean() {
    PropertiesFactoryBean bean = new PropertiesFactoryBean();
    bean.setLocations(
        "classpath:org/fxb/resources/**/first.properties",
        "classpath:org/fxb/resources/*/second.properties",
        "classpath:org/fxb/resources/properties/first-[profile].properties"
    );

    return bean;
  }

  @Bean
  public Properties profileConfig() throws Exception {
    return profilePropertiesFactoryBean().getObject();
  }
}
