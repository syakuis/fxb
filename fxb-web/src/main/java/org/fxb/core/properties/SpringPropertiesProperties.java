package org.fxb.core.properties;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 26.
 */
public class SpringPropertiesProperties implements Properties {
	private final Properties properties;
	private final String delimiter;

	public SpringPropertiesProperties(Properties properties, String delimiter) {
		this.properties = properties;
		this.delimiter = delimiter;
	}

	@Override
	public Iterator<String> getKeys() {
		return properties.getKeys();
	}

	@Override
	public String getString(String key) {
		return properties.getString(key);
	}

	@Override
	public String getString(String key, String defaultValue) {
		return properties.getString(key, defaultValue);
	}

	@Override
	public int getInt(String key) {
		return properties.getInt(key);
	}

	@Override
	public int getInt(String key, int defaultValue) {
		return properties.getInt(key, defaultValue);
	}

	@Override
	public long getLong(String key) {
		return properties.getLong(key);
	}

	@Override
	public long getLong(String key, long defaultValue) {
		return properties.getLong(key, defaultValue);
	}

	@Override
	public boolean getBoolean(String key) {
		return properties.getBoolean(key);
	}

	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		return properties.getBoolean(key, defaultValue);
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
