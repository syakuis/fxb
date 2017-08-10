package org.fxb.config;

import org.fxb.config.support.MessageSourceMatchingPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 31.
 */
@Configuration
public class MessageSourceConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(MessageSourceConfiguration.class);
	private MessageSourceMatchingPattern messageSourceMatchingPattern = new MessageSourceMatchingPattern();
	private MessageSource messageSource;

	private String parentBasename =
			"classpath*:org/hibernate/validator/message.properties," +
			"classpath*:org/fxb/i18n/message.properties," +
			"classpath*:org/fxb/**/i18n/message.properties";

	private Fxb fxb;
	public void setSalt(@Autowired Fxb fxb) {
		this.fxb = fxb;
	}

	private ResourceBundleMessageSource getResourceBundleMessageSource(String basename, MessageSource parentMessageSource) {
		String[] basenames = StringUtils.commaDelimitedListToStringArray(basename);

		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setCacheSeconds(fxb.getInt("messageSourceCacheSeconds"));
		messageSource.setDefaultEncoding(fxb.getCharset());
		if (parentMessageSource != null) {
			messageSource.setParentMessageSource(parentMessageSource);
		}
		try {
			String[] properties = messageSourceMatchingPattern.getResources(basenames);
			messageSource.setBasenames(properties);
			if (logger.isDebugEnabled()) {
				logger.debug(">< >< {}", Arrays.asList(properties));
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return messageSource;
	}

	private ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource(String basename, MessageSource parentMessageSource) {
		String[] basenames = StringUtils.commaDelimitedListToStringArray(basename);

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setCacheSeconds(fxb.getInt("messageSourceCacheSeconds"));
		messageSource.setDefaultEncoding(fxb.getCharset());
		messageSource.setConcurrentRefresh(fxb.getBoolean("messageSourceConcurrentRefresh"));
		if (parentMessageSource != null) {
			messageSource.setParentMessageSource(parentMessageSource);
		}
		try {
			String[] properties = messageSourceMatchingPattern.getResources(basenames);
			messageSource.setBasenames(properties);
			if (logger.isDebugEnabled()) {
				logger.debug(">< >< {}", Arrays.asList(properties));
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return messageSource;
	}

	private MessageSource getMessageSource() {
		if (fxb.getBoolean("messageSourceReloadable")) {
			return getReloadableResourceBundleMessageSource(parentBasename,
					getReloadableResourceBundleMessageSource(fxb.getString("messageSourceBasename"), null));
		}

		return getResourceBundleMessageSource(parentBasename,
				getResourceBundleMessageSource(fxb.getString("messageSourceBasename"),null));
	}

	@Bean
	public MessageSource messageSource() {
		this.messageSource = getMessageSource();
		return this.messageSource;
	}

	@Bean
	@DependsOn("messageSource")
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(this.messageSource);
	}
}
