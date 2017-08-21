package org.fxb.core.properties;

import org.apache.commons.configuration2.Configuration;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 22.
 */
public class CommonsConfigurationProperties implements Properties {
	private final Configuration configuration;
	private final String delimiter;

	public CommonsConfigurationProperties(Configuration configuration, String delimiter) {
		this.configuration = configuration;
		this.delimiter = delimiter;
	}

	@Override
	public String getString(String key) {
		return configuration.getString(key);
	}

	@Override
	public String getString(String key, String defaultValue) {
		return configuration.getString(key, defaultValue);
	}

	@Override
	public int getInt(String key) {
		return configuration.getInt(key);
	}

	@Override
	public int getInt(String key, int defaultValue) {
		return configuration.getInt(key, defaultValue);
	}

	@Override
	public long getLong(String key) {
		return configuration.getLong(key);
	}

	@Override
	public long getLong(String key, long defaultValue) {
		return configuration.getLong(key, defaultValue);
	}

	@Override
	public boolean getBoolean(String key) {
		return configuration.getBoolean(key);
	}

	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		return configuration.getBoolean(key, defaultValue);
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
		return (T) configuration.getProperty(key);
	}
}
