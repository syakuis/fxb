package org.fxb.context.config;

import java.io.IOException;
import org.fxb.config.Config;
import org.fxb.resource.bean.factory.MessageSourceFactoryBean;
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
@Configuration
public class MessageSourceConfiguration {
  private static final Logger logger = LoggerFactory.getLogger(MessageSourceConfiguration.class);

  private MessageSource messageSource;

  @Autowired
  private Config config;

  @Bean
  public MessageSource messageSource() throws IOException {
    Assert.notNull(config, "The class must not be null");

    String[] defaultBasenames = config.getStringArray("default.messageSource.basenames");

    MessageSourceFactoryBean parentFactoryBean = new MessageSourceFactoryBean();
    parentFactoryBean.setFileEncoding(config.getCharset());
    parentFactoryBean.setBasenames(defaultBasenames);
    parentFactoryBean.setCacheSeconds(config.getInt("messageSource.cacheSeconds"));
    parentFactoryBean.setConcurrentRefresh(config.getBoolean("messageSource.concurrentRefresh"));

    // Config 에 의한 필요한 메세지 로드 : ParentMessageSource 를 Overwrite 할 수 있다.
    String[] basenames = config.getStringArray("messageSource.basenames");
    if (basenames != null && basenames.length > 0) {
      MessageSourceFactoryBean factoryBean = new MessageSourceFactoryBean();
      factoryBean.setParentMessageSource(parentFactoryBean.getObject());
      factoryBean.setFileEncoding(config.getCharset());
      factoryBean.setBasenames(basenames);
      factoryBean.setCacheSeconds(config.getInt("messageSource.cacheSeconds"));
      factoryBean.setConcurrentRefresh(config.getBoolean("messageSource.concurrentRefresh"));

      this.messageSource = factoryBean.getObject();
    } else {
      this.messageSource = parentFactoryBean.getObject();
    }

    logger.debug("><>< messageSource Loader : {}, {}", defaultBasenames, basenames);

    return this.messageSource;
  }

  @Bean
  @DependsOn("messageSource")
  public MessageSourceAccessor messageSourceAccessor() {
    return new MessageSourceAccessor(this.messageSource);
  }
}
