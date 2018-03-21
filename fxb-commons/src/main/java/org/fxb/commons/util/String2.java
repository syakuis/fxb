package org.fxb.commons.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 3. 21.
 */
public class String2 {
  private static final Logger logger = LoggerFactory.getLogger(String2.class);

  public static String defaultIfEmpty(String... strings) {
      String result = null;

      for (String string : strings) {
        logger.debug("{} : {} = {}", string, result, StringUtils.defaultString(string, result));
        result =  StringUtils.defaultIfEmpty(string, result);
      }

    return result;
  }
}
