package org.fxb.core.properties;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 26.
 */
public class SpringPropertiesProperties implements Properties {
	private final java.util.Properties properties;
	private final String delimiter;

	public SpringPropertiesProperties(java.util.Properties properties, String delimiter) {
		this.properties = properties;
		this.delimiter = delimiter;
	}

	@Override
	public List<String> getKeys() {
		// todo java8 stream or java8
		Enumeration<Object> enumeration = properties.keys();
		List<String> result = new ArrayList<>();
		while(enumeration.hasMoreElements()) {
			result.add((String) enumeration.nextElement());
		};
		return result;
	}

	@Override
	public String getString(String key) {
		return properties.getProperty(key);
	}

	@Override
	public String getString(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	@Override
	public int getInt(String key) {
		return NumberUtils.toInt(properties.getProperty(key));
	}

	@Override
	public int getInt(String key, int defaultValue) {
		return NumberUtils.toInt(properties.getProperty(key), defaultValue);
	}

	@Override
	public long getLong(String key) {
		return NumberUtils.toLong(properties.getProperty(key));
	}

	@Override
	public long getLong(String key, long defaultValue) {
		return NumberUtils.toLong(properties.getProperty(key), defaultValue);
	}

	@Override
	public boolean getBoolean(String key) {
		return BooleanUtils.toBoolean(properties.getProperty(key));
	}

	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		if (StringUtils.isEmpty(properties.getProperty(key))) {
			return defaultValue;
		}
		return BooleanUtils.toBoolean(properties.getProperty(key));
	}

	@Override
	public <T> T getArray(String key) {
		return (T) StringUtils.delimitedListToStringArray(this.getString(key), this.delimiter);
	}

	@Override
	public String[] getStringArray(String key) {
		return this.getArray(key);
	}

	@Override
	public <T> List<T> getList(String key) {
		return Arrays.asList((T) this.getArray(key));
	}

	@Override
	public <T> T get(String key) {
		return (T) properties.get(key);
	}
}
