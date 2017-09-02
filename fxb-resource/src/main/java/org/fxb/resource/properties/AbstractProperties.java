package org.fxb.resource.properties;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 26.
 */
public abstract class AbstractProperties {
	protected final Properties properties;
	private String delimiter = ",";

	public AbstractProperties(Properties properties) {
		Assert.notNull(properties, "The class must not be null");
		this.properties = properties;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public List<String> getKeys() {
		// todo java8 stream or java8
		Enumeration<Object> enumeration = properties.keys();
		List<String> result = new ArrayList<>();
		while(enumeration.hasMoreElements()) {
			result.add((String) enumeration.nextElement());
		};
		return result;
	}

	public String getString(String key) {
		return properties.getProperty(key);
	}

	public String getString(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public int getInt(String key) {
		return NumberUtils.toInt(properties.getProperty(key));
	}

	public int getInt(String key, int defaultValue) {
		return NumberUtils.toInt(properties.getProperty(key), defaultValue);
	}

	public long getLong(String key) {
		return NumberUtils.toLong(properties.getProperty(key));
	}

	public long getLong(String key, long defaultValue) {
		return NumberUtils.toLong(properties.getProperty(key), defaultValue);
	}

	public boolean getBoolean(String key) {
		return BooleanUtils.toBoolean(properties.getProperty(key));
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		if (StringUtils.isEmpty(properties.getProperty(key))) {
			return defaultValue;
		}
		return BooleanUtils.toBoolean(properties.getProperty(key));
	}

	public <T> T getArray(String key) {
		return (T) StringUtils.split(this.getString(key), this.delimiter);
	}

	public String[] getStringArray(String key) {
		return this.getArray(key);
	}

	public <T> List<T> getList(String key) {
		return Arrays.asList((T) this.getArray(key));
	}

	public <T> T get(String key) {
		return (T) properties.get(key);
	}
}
