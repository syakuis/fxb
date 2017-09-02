package org.fxb.resource.bean.factory;

import org.fxb.util.io.MessageSourcePathMatchingResource;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 2.
 */
public class MessageSourceFactoryBean implements FactoryBean<MessageSource>, EnvironmentAware, ServletContextAware {
	private final MessageSourcePathMatchingResource messageSourceMatchingPattern = new MessageSourcePathMatchingResource();
	ServletContext servletContext;
	Environment environment;

	MessageSource parentMessageSource;
	String[] basenames;
	String fileEncoding = Charset.defaultCharset().name();
	int cacheSeconds = -1;
	boolean concurrentRefresh = true;
	boolean alwaysUseMessageFormat = false;

	String defaultEncoding;
	long cacheMillis = -1;
	Properties fileEncodings;
	PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	Properties commonMessages;
	boolean fallbackToSystemLocale = true;
	boolean useCodeAsDefaultMessage = false;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setParentMessageSource(MessageSource parentMessageSource) {
		this.parentMessageSource = parentMessageSource;
	}

	public void setBasename(String basename) {
		this.basenames = new String[]{ basename };
	}

	public void setBasenames(String[] basenames) {
		this.basenames = basenames;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
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

	@Override
	public MessageSource getObject() throws IOException {
		Assert.notEmpty(basenames, "The array must contain elements");

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setCacheSeconds(this.cacheSeconds);
		messageSource.setDefaultEncoding(this.fileEncoding);
		messageSource.setConcurrentRefresh(this.concurrentRefresh);
		messageSource.setAlwaysUseMessageFormat(this.alwaysUseMessageFormat);

		messageSource.setDefaultEncoding(this.defaultEncoding);
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
	public Class<MessageSource> getObjectType() {
		return MessageSource.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
