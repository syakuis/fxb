package org.fxb.commons.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public class Output {
  private static final Logger logger = LoggerFactory.getLogger(Output.class);
  private StringBuilder stringBuilder;
  private final String LINE = "---------------------------------------------------------------";
  private final String TITLE_MARK = "#";
  private final String BR = "\n";

  public Output(String title) {
    stringBuilder = new StringBuilder();
    stringBuilder
      .append(BR)
      .append(LINE).append(BR)
      .append(TITLE_MARK).append(" ").append(title).append(BR)
      .append(BR);
  }

  private void wrapper(String string) {
    stringBuilder.append("><>< ").append(string).append(BR);
  }

  public void append(String string) {
    wrapper(string);
  }

  public void append(boolean isLine, String... strings) {
    StringBuilder sb = new StringBuilder();

    for (String string : strings) {
      if (isLine) {
        wrapper(string);
      } else {
        sb.append(string);
      }
    }

    if (!isLine) {
      wrapper(sb.toString());
    }
  }

  public void end() {
    logger.debug(stringBuilder.append(LINE).toString());
  }


}
