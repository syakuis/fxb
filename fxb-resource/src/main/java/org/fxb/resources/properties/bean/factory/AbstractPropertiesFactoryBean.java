package org.fxb.resources.properties.bean.factory;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * 여러 프로퍼티를 읽어서 하나를 만들어준다.
 *
 * Ant pattern style 로 프로퍼티를 찾을 수 있다.
 * profile 속성을 이용하여 서버 환경에 맞는 프로퍼티를 읽을 수 있다.
 * 프로퍼티를 순차적으로 읽어 덮어쓰기가 가능하다.
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2017. 8. 26.
 * @see Properties
 * @see PropertiesFactoryBean
 */
public abstract class AbstractPropertiesFactoryBean implements FactoryBean<Properties>,
    EnvironmentAware, InitializingBean {
  private final Logger logger = LoggerFactory.getLogger(AbstractPropertiesFactoryBean.class);

  private static final String FILE_ENCODING_NAME = "file.encoding";
  private static final String FILE_ENCODING = "utf-8";
  private static final String PROFILE = "\\[profile\\]";

  private Environment environment;
  private String fileEncoding;
  private String[] locations;
  private String profile;

  private boolean singleton = true;

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  public void setFileEncoding(String fileEncoding) {
    this.fileEncoding = fileEncoding;
  }

  public void setLocations(String... locations) {
    this.locations = locations;
  }

  /**
   * 서버 환경에 맞는 프로퍼티를 로드할때 사용한다.
   * @param profile
   */
  public void setProfile(String profile) {
    this.profile = profile;
  }

  public void setSingleton(boolean singleton) {
    this.singleton = singleton;
  }

  protected String getFileEncoding() {
    Assert.notNull(environment, "The class must not be null. (environment)");

    this.fileEncoding = Charset.defaultCharset().name();

    String encoding = StringUtils.defaultString(
        environment.getProperty(this.FILE_ENCODING_NAME),
        StringUtils.defaultString(new String(this.fileEncoding), this.FILE_ENCODING)
    );

    return encoding;
  }

  protected Environment getEnvironment() {
    return environment;
  }

  /**
   * locations 경로 문자열에 "[profile]" 구문이 있으면 profile 속성으로 치환한다.
   * @return
   */
  private String[] getLocationProfileReplace() {
    Assert.notNull(profile, "the profile argument must not be null.");

    List<String> result = new ArrayList<>();
    for (String location : locations) {
      result.add(location.replaceAll(this.PROFILE, profile));
    }

    return result.toArray(new String[result.size()]);
  }

  protected String[] getLocations() {
    if (this.profile != null) {
      this.locations = this.getLocationProfileReplace();
    }

    if (logger.isDebugEnabled()) {
      for (String location : locations) {
        logger.debug("locations : {} added", location);
      }
    }

    return locations;
  }

  @Override
  public abstract Properties getObject() throws Exception;

  @Override
  public abstract void afterPropertiesSet() throws Exception;

  @Override
  public Class<Properties> getObjectType() {
    return Properties.class;
  }

  @Override
  public boolean isSingleton() {
    return this.singleton;
  }
}
