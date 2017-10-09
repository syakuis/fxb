package org.fxb.commons.io;

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
 * {@link PathMatchingResourcePatternResolver} 를 이용하여 여러개의 경로 패턴을 이용하여 메세지 프로퍼티의 경로를 구한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 6. 7.
 *
 * @see PathMatchingResourcePatternResolver
 */
public class MessageSourcePathMatchingResource {
	private static final Logger logger = LoggerFactory.getLogger(MessageSourcePathMatchingResource.class);
	private final String extension = ".properties";

	private final PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver
			= new PathMatchingResourcePatternResolver();

	public String getResource(String basenamePattern) throws IOException {
		return getBaseName(pathMatchingResourcePatternResolver.getResource(basenamePattern));
	}

	public String[] getResources(String basenamePatterns) throws IOException {
		return getResources(
				org.springframework.util.StringUtils.delimitedListToStringArray(basenamePatterns, ","));
	}

	/**
	 * Get resources string [ ].
	 *
	 * locationPattern = {
	 *     "classpath:org/saltframework/{@code **}/i18n/message.properties"
	 * }
	 * @param basenamesPattern
	 * @return
	 * @throws IOException
	 */
	public String[] getResources(String[] basenamesPattern) throws IOException {

		List<String> basenames = new ArrayList();

		for (String basenamePattern : basenamesPattern) {
			Resource[] resources = pathMatchingResourcePatternResolver.getResources(basenamePattern.trim());
			for (Resource resource : resources) {
				if (resource.exists()) {
					String basename = getBaseName(resource);
					if (basename != null) {
						basenames.add(basename);
						logger.debug("><>< MessageSource add basename : {}", basename);
					}
				}
			}
		}

		return basenames.toArray(new String[basenames.size()]);
	}

	private String getBaseName(Resource resource) throws IOException {
		try {
			String uri = resource.getURI().toString();
			String basename = null;

			if (resource instanceof FileSystemResource) {
				basename = StringUtils.substringBefore(uri, extension);
			} else if (resource instanceof ClassPathResource) {
				basename = StringUtils.substringBefore(uri, extension);
			} else if (resource instanceof UrlResource) {
				String path = StringUtils.substringBetween(uri, ".jar!/", extension);
				if (path == null) {
					basename = StringUtils.substringBefore(uri, extension);
				} else {
					basename = "classpath:" + StringUtils.substringBetween(uri, ".jar!/", extension);
				}
			}

			if (basename != null) {
				return processBasename(basename);
			}

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	private String processBasename(String basename) {
		String prefix = StringUtils.substringBeforeLast(basename, "/");
		String name = StringUtils.substringAfterLast(basename, "/");
		do {
			name = StringUtils.substringBeforeLast(name, "_");
		} while (name.contains("_"));
		return prefix + "/" + name;
	}
}
