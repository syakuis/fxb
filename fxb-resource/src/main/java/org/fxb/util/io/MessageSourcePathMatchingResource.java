package org.fxb.util.io;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 모든 message source 찾는 다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 6. 7.
 */
public class MessageSourcePathMatchingResource {
	private static final Logger logger = LoggerFactory.getLogger(MessageSourcePathMatchingResource.class);

	private final PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

	public String getResource(String location) throws IOException {
		return getBaseName(pathMatchingResourcePatternResolver.getResource(location));
	}

	public String[] getResources(String locationPattern) throws IOException {
		return getResources(new String[]{locationPattern});
	}

	/**
	 * Get resources string [ ].
	 *
	 * locationPattern = {
	 *     "classpath:org/saltframework/{@code **}/i18n/message.properties"
	 * }
	 *
	 * @param locationPattern the location pattern
	 * @return the string [ ]
	 * @throws IOException the io exception
	 */
	public String[] getResources(String[] locationPattern) throws IOException {

		List<String> strings = new ArrayList();

		for (String path : locationPattern) {
			Resource[] resources = pathMatchingResourcePatternResolver.getResources(path);
			for (Resource resource : resources) {
				//strings.add(resource.createRelative(MESSAGE_NAME));
				String baseName = getBaseName(resource);
				if (baseName != null) strings.add(baseName);
			}
		}

		return strings.toArray(new String[strings.size()]);
	}

	private String getBaseName(Resource resource) throws IOException {
		try {
			String uri = resource.getURI().toString();
			String baseName = null;

			if (resource instanceof FileSystemResource) {
				// baseName = "classpath:" + substringBetween(uri, "/classes/", ".properties");
				baseName = StringUtils.substringBefore(uri, ".properties");
			} else if (resource instanceof ClassPathResource) {
				baseName = StringUtils.substringBefore(uri, ".properties");
			} else if (resource instanceof UrlResource) {
				baseName = "classpath:" + StringUtils.substringBetween(uri, ".jar!/", ".properties");
			}

			if (baseName != null) {
				return processBasename(baseName);
			}

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	private String processBasename(String baseName) {
		String prefix = StringUtils.substringBeforeLast(baseName, "/");
		String name = StringUtils.substringAfterLast(baseName, "/");
		do {
			name = StringUtils.substringBeforeLast(name, "_");
		} while (name.contains("_"));
		return prefix + "/" + name;
	}
}
