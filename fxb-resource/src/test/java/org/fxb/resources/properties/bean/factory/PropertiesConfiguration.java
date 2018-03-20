package org.fxb.resources.properties.bean.factory;

import java.io.IOException;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 3. 19.
 */
@Configuration
@TestPropertySource(locations = {
    "/org/fxb/resources/properties/config.properties"
})
public class PropertiesConfiguration {

  @Value("${profile}")
  private String profile;

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
  public Properties config() throws IOException {
    return propertiesFactoryBean().getObject();
  }

  @Bean
  public PropertiesFactoryBean stringLocationsPropertiesFactoryBean() {
    PropertiesFactoryBean bean = new PropertiesFactoryBean();
    bean.setLocations("classpath:org/fxb/resources/**/first.properties, classpath:org/fxb/resources/*/second.properties");

    return bean;
  }

  @Bean
  public Properties stringPathConfig() throws IOException {
    return stringLocationsPropertiesFactoryBean().getObject();
  }

  @Bean
  public PropertiesFactoryBean profilePropertiesFactoryBean() {
    Assert.notNull(profile, "the profile must not be null.");

    PropertiesFactoryBean bean = new PropertiesFactoryBean();
    bean.setProfile(profile);
    bean.setLocations(
        "classpath:org/fxb/resources/**/first.properties",
        "classpath:org/fxb/resources/*/second.properties",
        "classpath:org/fxb/resources/properties/first-[profile].properties"
    );

    return bean;
  }

  @Bean
  public Properties profileConfig() throws IOException {
    return profilePropertiesFactoryBean().getObject();
  }
}
