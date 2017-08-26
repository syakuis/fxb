package org.fxb.core.properties;

import org.fxb.core.properties.exceptions.PropertiesException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 22.
 */
public interface PropertiesLoader {
	void setFileEncoding(String fileEncoding);
	void setDelimiter(String delimiter);
	Properties getProperties() throws PropertiesException;
}
