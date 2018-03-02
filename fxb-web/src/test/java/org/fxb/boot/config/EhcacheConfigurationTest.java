package org.fxb.boot.config;

import org.fxb.config.prepared.PropertiesConfiguration;
import org.fxb.config.context.EhcacheConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Ehcache Configuration BootLoader Test
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { PropertiesConfiguration.class, EhcacheConfiguration.class })
@ActiveProfiles("test")
public class EhcacheConfigurationTest {

	@Resource(name = "fxbCacheManager")
	private CacheManager cacheManager;

	@Test
	public void test() throws InterruptedException {
		String title = "ehcache testing...";
		Cache cache = cacheManager.getCache("test");

		cache.put("title", title);
		Assert.assertEquals(cache.get("title").get(), title);

		Thread.sleep(5000);

		Assert.assertEquals(cache.get("title").get(), title);

		Thread.sleep(10000);
		Assert.assertNull(cache.get("title"));

	}
}
