package org.fxb.context.cache;

import org.fxb.context.cache.config.EhcacheConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = EhcacheConfiguration.class)
public class CacheConfigurationTest {
	@Autowired
	private CacheManager cacheManager;

	@Resource(name = "cacheServiceTest")
	private CacheServiceTest cacheServiceTest;

	@Test
	public void test() throws InterruptedException {
		// 현재 시간을 캐시에 저장한다.
		System.out.println("#1 ==========>" + cacheServiceTest.getDate());

		Thread.sleep(5000);
		// 저장된 캐시를 삭제하고 현재 시간을 캐시에 저장한다.
		System.out.println("#2 ==========>" + cacheServiceTest.evict());

		Thread.sleep(5000);
		// 저장되어 있는 캐시를 얻는 다. (이미 저장되어 있는 데이터가 있기 때문에)
		System.out.println("#3 ==========>" + cacheServiceTest.getDate());

		Thread.sleep(5000);
		// 저장되어 있는 캐시를 현재 시간으로 갱신한다.
		System.out.println("#4 ==========>" + cacheServiceTest.put());

		Thread.sleep(5000);
		// 저장되어 있는 캐시를 얻는 다. (이미 저장되어 있는 데이터가 있기 때문에)
		System.out.println("#5 ==========>" + cacheServiceTest.getDate());

		Thread.sleep(10000);
		// 캐시 갱신기간이 만료되어 삭제되었다. 그리고 현재 시간을 캐시에 저장한다.
		System.out.println("#6 ==========>" + cacheServiceTest.getDate());
	}
}
