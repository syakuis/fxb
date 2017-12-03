package org.fxb.web.module.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 12. 1.
 */
@Getter
@EqualsAndHashCode
public class OptionDetails implements Option {
  private final String name;
  // todo generic type
  private String value;
  // todo comment change
  private String title;
  private int order;

  public OptionDetails(String name) {
    this(name, null, null, 0);
  }

  public OptionDetails(String name, String value, int order) {
    this(name, value, null, order);
  }

  public OptionDetails(String name, String value, String title, int order) {
    this.name = name;
    this.value = value;
    this.title = title;
    this.order = order;
  }
}
