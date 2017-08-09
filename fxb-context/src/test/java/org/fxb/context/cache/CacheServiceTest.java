package org.fxb.context.cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 7. 27.
 */
@Service
public class CacheServiceTest {

	@Cacheable("test")
	public String getDate() {
		return new Date().toString();
	}

	@CacheEvict("test")
	public String evict() {
		return new Date().toString();
	}

	@CachePut("test")
	public String put() {
		return new Date().toString();
	}

	@Cacheable(cacheNames = "test", key = "#root.methodName")
	public String getDateKey() {
		return new Date().toString();
	}

	@Cacheable(cacheNames = "test", key = "#root.methodName")
	public String getDateKey2() {
		return new Date().toString();
	}

	@CacheEvict(value = "test", key = "#key")
	public String evictKey(String key) {
		return new Date().toString();
	}

	@CachePut(value = "test", key = "#key")
	public String putKey(String key) {
		return new Date().toString();
	}
}