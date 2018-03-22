package org.fxb.context.config;

import java.io.IOException;
import org.fxb.context.Config;
import org.fxb.resources.i18n.bean.factory.MessageSourceFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.util.Assert;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 31.
 */
//@Configuration
public class MessageSourceConfiguration {
  private static final Logger logger = LoggerFactory.getLogger(MessageSourceConfiguration.class);
//
//  private MessageSource messageSource;
//
//  @Autowired
//  private Config config;
//
//  @Bean
//  public MessageSourceFactoryBean parentMessageSourceFactoryBean() {
//    MessageSourceFactoryBean bean = new MessageSourceFactoryBean();
//    bean.setBaseNames(config.getStringArray("default.messageSource.basenames"));
//    bean.setCacheSeconds(config.getInt("messageSource.cacheSeconds"));
//    bean.setConcurrentRefresh(config.getBoolean("messageSource.concurrentRefresh"));
//
//    return bean;
//  }
//
//  @Bean
//  public MessageSourceFactoryBean messageSourceFactoryBean() throws Exception {
//    MessageSourceFactoryBean bean = new MessageSourceFactoryBean();
//    bean.setParentMessageSource(parentMessageSourceFactoryBean().getObject());
//    bean.setBaseNames(config.getStringArray("messageSource.basenames"));
//    bean.setCacheSeconds(config.getInt("messageSource.cacheSeconds"));
//    bean.setConcurrentRefresh(config.getBoolean("messageSource.concurrentRefresh"));
//
//    return bean;
//  }
//
//  @Bean
//  public MessageSource messageSource() throws Exception {
//    Assert.notNull(config, "The class must not be null");
//    // Config 에 의한 필요한 메세지 로드 : ParentMessageSource 를 Overwrite 할 수 있다.
//    String[] basenames = config.getStringArray("messageSource.basenames");
//    if (basenames != null && basenames.length > 0) {
//      return messageSourceFactoryBean().getObject();
//    }
//
//    return parentMessageSourceFactoryBean().getObject();
//  }
//
//  @Bean
//  @DependsOn("messageSource")
//  public MessageSourceAccessor messageSourceAccessor() {
//    return new MessageSourceAccessor(this.messageSource);
//  }
}
