package org.fxb.context.cache.bean.factory;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;
import org.fxb.context.cache.bean.factory.support.EhcacheConfigurationException;
import org.fxb.context.cache.bean.factory.support.EhcacheConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * todo EhCacheManagerFactoryBean 상속하여 리팩토리하기
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 7. 28.
 * @see org.springframework.cache.ehcache.EhCacheManagerFactoryBean
 */
public class EhcacheFactoryBean implements FactoryBean<CacheManager>, InitializingBean, DisposableBean {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private CacheManager cacheManager;

	private final String ehcacheLocation;
	private final String[] cacheLocations;

	private String charset = "UTF-8";

	private String cacheManagerName = "cacheManager";

	private boolean acceptExisting = false;

	private boolean shared = false;

	private boolean locallyManaged = true;

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public void setCacheManagerName(String cacheManagerName) {
		this.cacheManagerName = cacheManagerName;
	}

	public void setAcceptExisting(boolean acceptExisting) {
		this.acceptExisting = acceptExisting;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public void setLocallyManaged(boolean locallyManaged) {
		this.locallyManaged = locallyManaged;
	}

	public EhcacheFactoryBean(String ehcacheLocation, String cacheLocation) {
		this(ehcacheLocation, StringUtils.delimitedListToStringArray(cacheLocation, ","));
	}

	public EhcacheFactoryBean(String ehcacheLocation, String[] cacheLocations) {
		this.ehcacheLocation = ehcacheLocation;
		this.cacheLocations = cacheLocations;
	}

	@Override
	public void afterPropertiesSet() throws CacheException, EhcacheConfigurationException {
		logger.info("><>< Initializing EhCache CacheManager");

		EhcacheConfigurationLoader loader = new EhcacheConfigurationLoader(ehcacheLocation, cacheLocations);
		loader.setCharset(charset);
		Configuration configuration = loader.create();

		Assert.notNull(configuration, "The class must not be null");

		if (this.cacheManagerName != null) {
			configuration.setName(this.cacheManagerName);
		}

		if (this.shared) {
			// Old-school EhCache singleton sharing...
			// No way to find out whether we actually created a new CacheManager
			// or just received an existing singleton reference.
			this.cacheManager = CacheManager.create(configuration);
		}
		else if (this.acceptExisting) {
			// EhCache 2.5+: Reusing an existing CacheManager of the same name.
			// Basically the same code as in CacheManager.getInstance(String),
			// just storing whether we're dealing with an existing instance.
			synchronized (CacheManager.class) {
				this.cacheManager = CacheManager.getCacheManager(this.cacheManagerName);
				if (this.cacheManager == null) {
					this.cacheManager = new CacheManager(configuration);
				}
				else {
					this.locallyManaged = false;
				}
			}
		}
		else {
			// Throwing an exception if a CacheManager of the same name exists already...
			this.cacheManager = new CacheManager(configuration);
		}
	}


	@Override
	public CacheManager getObject() {
		return this.cacheManager;
	}

	@Override
	public Class<? extends CacheManager> getObjectType() {
		return (this.cacheManager != null ? this.cacheManager.getClass() : CacheManager.class);
	}

	@Override
	public boolean isSingleton() {
		return true;
	}


	@Override
	public void destroy() {
		if (this.locallyManaged) {
			logger.info("Shutting down EhCache CacheManager");
			this.cacheManager.shutdown();
		}
	}
}