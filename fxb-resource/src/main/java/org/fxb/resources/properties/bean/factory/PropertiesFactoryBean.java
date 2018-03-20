package org.fxb.resources.properties.bean.factory;

import java.io.IOException;
import java.util.Properties;
import org.fxb.resources.properties.PropertiesLoader;
import org.springframework.util.StringUtils;

/**
 * {@link AbstractPropertiesFactoryBean} 구현체
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2017. 8. 29.
 *
 * @see AbstractPropertiesFactoryBean
 * @see PropertiesLoader
 */
public class PropertiesFactoryBean extends AbstractPropertiesFactoryBean {
  private Properties singletonInstance;

  public void setLocations(String locations) {
    this.setLocations(StringUtils.tokenizeToStringArray(locations, ","));
  }

  private Properties createObject() throws IOException {
    PropertiesLoader loader = new PropertiesLoader();
    loader.setLocations(this.getLocations());
    String fileEncoding = this.getFileEncoding();
    if (fileEncoding != null) {
      loader.setFileEncoding(fileEncoding);
    }
    return loader.getProperties();
  }

  @Override
  public Properties getObject() throws IOException {
    if (this.isSingleton()) {
      return this.singletonInstance;
    }
    return createObject();
  }

  @Override
  public void afterPropertiesSet() throws IOException {
    if (this.isSingleton()) {
      this.singletonInstance = createObject();
    }
  }
}
