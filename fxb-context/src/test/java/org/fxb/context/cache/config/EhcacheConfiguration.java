package org.fxb.context.cache.config;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import org.fxb.context.cache.bean.factory.EhcacheFactoryBean;
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
@ComponentScan(
		basePackages = "org.fxb.context",
		includeFilters =  {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class)
		}
)
public class EhcacheConfiguration implements CachingConfigurer {

	private net.sf.ehcache.config.Configuration getConfiguration() {


		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setName("test");
		cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
		cacheConfiguration.setMaxEntriesLocalHeap(0);
		cacheConfiguration.setEternal(false);
		cacheConfiguration.setTimeToIdleSeconds(0);
		cacheConfiguration.setTimeToLiveSeconds(0);
		cacheConfiguration.setLogging(false);
		cacheConfiguration.persistence(
				new PersistenceConfiguration()
						.strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP)
		);

		net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
		configuration.setDynamicConfig(true);
		configuration.setUpdateCheck(true);
		configuration.setMonitoring("autodetect");

		configuration.addCache(cacheConfiguration);

		return configuration;
	}

	@Bean(destroyMethod="shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager() {
		/*
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setName("default");
		cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
		cacheConfiguration.setMaxEntriesLocalHeap(1000);
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(cacheConfiguration);
		return net.sf.ehcache.CacheManager.newInstance(config);
		*/

		EhcacheFactoryBean factoryBean = new EhcacheFactoryBean();
		factoryBean.setConfiguration(this.getConfiguration());
		factoryBean.afterPropertiesSet();

		return factoryBean.getObject();
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
