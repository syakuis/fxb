package org.fxb.app.module.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
@Getter
@Setter
@ToString
public class OptionDetails implements Option {
  private final String name;
  private final int order;
  private String value;
  private String title;

  public OptionDetails(String name, int order) {
    this(name, order, null, null);
  }

  public OptionDetails(String name, int order, String value, String title) {
    this.name = name;
    this.order = order;
    this.value = value;
    this.title = title;
  }
}
