package org.fxb.resource.bean.factory;

import java.io.IOException;
import java.util.Properties;
import org.fxb.commons.io.MessageSourcePathMatchingResource;
import org.fxb.commons.environment.Env;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * todo ReloadableResourceBundleMessageSource 상속하여 리팩토리하기.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 2.
 */
public class MessageSourceFactoryBean implements FactoryBean<MessageSource>,
    EnvironmentAware, InitializingBean {
  private final MessageSourcePathMatchingResource messageSourceMatchingPattern = new MessageSourcePathMatchingResource();
  private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

  private MessageSource instance;
  private Environment environment;
  private boolean singleton = true;

  private MessageSource parentMessageSource;
  private String[] basenames;
  int cacheSeconds = -1;
  boolean concurrentRefresh = true;
  boolean alwaysUseMessageFormat = false;
  private String defaultEncoding;
  long cacheMillis = -1;
  private Properties fileEncodings;
  private Properties commonMessages;
  boolean fallbackToSystemLocale = true;
  boolean useCodeAsDefaultMessage = false;

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  public void setSingleton(boolean singleton) {
    this.singleton = singleton;
  }

  public void setParentMessageSource(MessageSource parentMessageSource) {
    this.parentMessageSource = parentMessageSource;
  }

  public void setBasenames(String... basenames) {
    this.basenames = basenames;
  }

  public void setAlwaysUseMessageFormat(boolean alwaysUseMessageFormat) {
    this.alwaysUseMessageFormat = alwaysUseMessageFormat;
  }

  /**
   * 캐시 타임 설정.
   * @param cacheSeconds
   */
  public void setCacheSeconds(int cacheSeconds) {
    this.cacheSeconds = cacheSeconds;
  }

  /**
   * 새로고침 여부를 설정한다.
   * @param concurrentRefresh
   */
  public void setConcurrentRefresh(boolean concurrentRefresh) {
    this.concurrentRefresh = concurrentRefresh;
  }

  public void setDefaultEncoding(String defaultEncoding) {
    this.defaultEncoding = defaultEncoding;
  }

  public void setCacheMillis(long cacheMillis) {
    this.cacheMillis = cacheMillis;
  }

  public void setFileEncodings(Properties fileEncodings) {
    this.fileEncodings = fileEncodings;
  }

  public void setPropertiesPersister(PropertiesPersister propertiesPersister) {
    this.propertiesPersister = propertiesPersister;
  }

  public void setCommonMessages(Properties commonMessages) {
    this.commonMessages = commonMessages;
  }

  public void setFallbackToSystemLocale(boolean fallbackToSystemLocale) {
    this.fallbackToSystemLocale = fallbackToSystemLocale;
  }

  public void setUseCodeAsDefaultMessage(boolean useCodeAsDefaultMessage) {
    this.useCodeAsDefaultMessage = useCodeAsDefaultMessage;
  }

  protected String getDefaultEncoding() {
    Assert.notNull(environment, "The class must not be null. (environment)");
    return Env.defaultFileEncoding(environment.getProperty("file.encoding"), defaultEncoding);
  }

  private MessageSource createObject() throws IOException {
    Assert.notEmpty(basenames, "The array must contain elements : basenames");

    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setCacheSeconds(this.cacheSeconds);
    messageSource.setConcurrentRefresh(this.concurrentRefresh);
    messageSource.setAlwaysUseMessageFormat(this.alwaysUseMessageFormat);

    messageSource.setDefaultEncoding(this.getDefaultEncoding());
    messageSource.setCacheMillis(this.cacheMillis);
    messageSource.setFileEncodings(this.fileEncodings);
    messageSource.setPropertiesPersister(this.propertiesPersister);
    messageSource.setCommonMessages(this.commonMessages);
    messageSource.setFallbackToSystemLocale(this.fallbackToSystemLocale);
    messageSource.setUseCodeAsDefaultMessage(this.useCodeAsDefaultMessage);

    if (parentMessageSource != null) {
      messageSource.setParentMessageSource(parentMessageSource);
    }

    String[] basenames = messageSourceMatchingPattern.getResources(this.basenames);

    if (basenames == null) {
      return messageSource;
    }

    messageSource.setBasenames(basenames);

    return messageSource;
  }


  @Override
  public void afterPropertiesSet() throws IOException {
    if (this.isSingleton()) {
      this.instance = this.createObject();
    }
  }

  @Override
  public MessageSource getObject() throws IOException {
    if (this.isSingleton()) {
      return this.instance;
    }

    return this.createObject();
  }

  @Override
  public Class<MessageSource> getObjectType() {
    return MessageSource.class;
  }

  @Override
  public boolean isSingleton() {
    return this.singleton;
  }
}
