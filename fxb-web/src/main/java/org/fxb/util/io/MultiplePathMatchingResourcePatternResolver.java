package org.fxb.util.io;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PathMatchingResourcePatternResolver 를 이용하여 여러개의 경로 패턴을 이용하여 메세지 프로퍼티의 경로를 구한다.
 *
 * @see PathMatchingResourcePatternResolver
 * @date 2015. 10. 1.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 */
public class MultiplePathMatchingResourcePatternResolver implements ResourcePatternResolver {
	private final PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

	@Override
	public ClassLoader getClassLoader() {
		return pathMatchingResourcePatternResolver.getClassLoader();
	}

	@Override
	public Resource getResource(String location) {
		return pathMatchingResourcePatternResolver.getResource(location);
	}

	@Override
	public Resource[] getResources(String locationPattern) throws IOException {
		return getResources(new String[]{locationPattern});
	}

	public Resource[] getResources(String[] locationPattern) throws IOException {
		List<Resource> listResource = new ArrayList<>();

		for (String path : locationPattern) {
			Resource[] resources = pathMatchingResourcePatternResolver.getResources(path);
			for (Resource resource : resources) {
				listResource.add(resource);
			}
		}

		return listResource.toArray(new Resource[listResource.size()]);
	}
}