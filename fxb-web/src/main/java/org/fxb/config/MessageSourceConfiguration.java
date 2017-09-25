package org.fxb.config;

import org.fxb.resource.bean.factory.MessageSourceFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 31.
 */
@Configuration
public class MessageSourceConfiguration {
	private MessageSource messageSource;

	@Autowired
	private Config config;

	/**
	 * 기본으로 읽어야할 메세지 로드
	 */
	private String[] basenames = new String[] {
			"classpath*:org/hibernate/validator/message.properties",
			"classpath*:org/fxb/config/i18n/message.properties",
			"classpath*:org/fxb/config/i18n/message_*.properties",
	};

	@Bean
	@DependsOn("config")
	public MessageSource messageSource() throws IOException {
		Assert.notNull(config, "The class must not be null");

		MessageSourceFactoryBean parentFactoryBean = new MessageSourceFactoryBean();
		parentFactoryBean.setFileEncoding(config.getCharset());
		parentFactoryBean.setBasenames(basenames);
		parentFactoryBean.setCacheSeconds(config.getInt("messageSource.cacheSeconds"));
		parentFactoryBean.setConcurrentRefresh(config.getBoolean("messageSource.concurrentRefresh"));

		// 설정에 의한 필요한 메세지 로드
		String[] basenames = config.getStringArray("messageSource.basename");
		if (basenames != null && basenames.length > 0) {
			MessageSourceFactoryBean factoryBean = new MessageSourceFactoryBean();
			factoryBean.setParentMessageSource(parentFactoryBean.getObject());
			factoryBean.setFileEncoding(config.getCharset());
			factoryBean.setBasenames(config.getStringArray("messageSource.basename"));
			factoryBean.setCacheSeconds(config.getInt("messageSource.cacheSeconds"));
			factoryBean.setConcurrentRefresh(config.getBoolean("messageSource.concurrentRefresh"));

			this.messageSource = factoryBean.getObject();
		} else {
			this.messageSource = parentFactoryBean.getObject();
		}

		return this.messageSource;
	}

	@Bean
	@DependsOn("messageSource")
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(this.messageSource);
	}
}
