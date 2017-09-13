package org.fxb.config;

import org.fxb.context.cache.bean.factory.EhcacheFactoryBean;
import org.fxb.context.cache.bean.factory.support.EhcacheConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 7. 22.
 */
@Configuration
@EnableCaching
public class CacheConfiguration implements CachingConfigurer {
	private final Logger logger = LoggerFactory.getLogger(CacheConfiguration.class);

	@Autowired
	private Config config;

	@Bean(destroyMethod="shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager() {

		try {
			EhcacheFactoryBean factoryBean = new EhcacheFactoryBean(
					config.getString("ehcache.ehcacheLocation"),
					config.getString("ehcache.cacheLocation")
			);

			factoryBean.setCharset(config.getCharset());
			factoryBean.setCacheManagerName(config.getString("ehcache.cacheManagerName"));
			factoryBean.setAcceptExisting(config.getBoolean("ehcache.acceptExisting"));
			factoryBean.setLocallyManaged(config.getBoolean("ehcache.locallyManaged"));
			factoryBean.setShared(config.getBoolean("ehcache.shared"));
			factoryBean.afterPropertiesSet();

			return factoryBean.getObject();
		} catch (EhcacheConfigurationException e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}

	@Bean
	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}
}
