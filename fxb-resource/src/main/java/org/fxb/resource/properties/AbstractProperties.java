package org.fxb.resource.properties;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 26.
 */
public abstract class AbstractProperties {
	protected final String delimiter = ",";
	protected final Properties properties;

	public AbstractProperties(Properties properties) {
		Assert.notNull(properties, "The class must not be null");
		this.properties = properties;
	}

	public List<String> getKeys() {
		return getKeys(null);
	}

	public List<String> getKeys(String prefix) {
		// todo java8 stream or java8
		Enumeration<Object> enumeration = properties.keys();
		List<String> result = new ArrayList<>();
		while(enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			if (prefix == null) {
				result.add(key);
			} else if (org.apache.commons.lang3.StringUtils.startsWith(key, prefix)) {
				result.add(key);
			}
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

	public <T> T get(String key) {
		return (T) properties.get(key);
	}

	public <T> T getArray(String key) {
		return this.getArray(key, delimiter, true);
	}
	public <T> T getArray(String key, String delimiter, boolean trim) {
		if (trim) {
			return (T) StringUtils.trimArrayElements(StringUtils.delimitedListToStringArray(this.getString(key), delimiter));
		}

		return (T) StringUtils.delimitedListToStringArray(this.getString(key), delimiter);
	}

	public String[] getStringArray(String key) {
		return this.getStringArray(key, delimiter, true);
	}

	public String[] getStringArray(String key, String delimiter, boolean trim) {
		return this.getArray(key, delimiter, trim);
	}

	public <T> List<T> getList(String key) {
		return this.getList(key, delimiter, true);
	}

	public <T> List<T> getList(String key, String delimiter, boolean trim) {
		return Arrays.asList(this.getArray(key, delimiter, trim));
	}
}
