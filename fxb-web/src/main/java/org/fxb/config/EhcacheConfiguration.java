package org.fxb.config;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.context.annotation.Configuration;

/**
 * fxb-web 에서 사용하는 전용 캐시
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 7. 22.
 */
@Configuration
@EnableCaching
public class EhcacheConfiguration implements CachingConfigurer {
  private final Logger logger = LoggerFactory.getLogger(EhcacheConfiguration.class);

  @Autowired
  private Config config;

  @Bean(destroyMethod="shutdown")
  public net.sf.ehcache.CacheManager ehCacheManager() {
    StringBuilder builder = new StringBuilder(config.getString("default.ehcache.cacheLocation"));
    String cacheLocation = config.getString("ehcache.cacheLocation");
    if (StringUtils.isNotEmpty(cacheLocation)) {
      builder.append(",").append(cacheLocation);
    }

    cacheLocation = builder.toString();

    try {
      EhcacheFactoryBean factoryBean = new EhcacheFactoryBean(
          config.getString("default.ehcache.ehcacheLocation"),
          cacheLocation
      );

//      logger.debug("><>< cacheLocation: {}", cacheLocation);
      factoryBean.setCacheManagerName(config.getString("default.ehcache.cacheManagerName"));
      factoryBean.afterPropertiesSet();

      return factoryBean.getObject();
    } catch (EhcacheConfigurationException e) {
      logger.error(e.getMessage(), e);
    }

    return null;
  }

  @Bean("fxbCacheManager")
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
