package org.fxb.core.properties;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 22.
 */
public interface Properties {
	String getString(String key);
	String getString(String key, String defaultValue);

	int getInt(String key);
	int getInt(String key, int defaultValue);

	long getLong(String key);
	long getLong(String key, long defaultValue);

	boolean getBoolean(String key);
	boolean getBoolean(String key, boolean defaultValue);

	<T> T getArray(String key);

	String[] getStringArray(String key);

	<T> List<T> getList(String key);

	<T> T get(String key);
}
