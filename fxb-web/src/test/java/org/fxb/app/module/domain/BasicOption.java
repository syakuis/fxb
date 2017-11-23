package org.fxb.app.module.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.fxb.app.module.model.Option;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
@Getter
@Setter
@ToString
public class BasicOption implements Option {
  private String moduleIdx;
  private int order;
  private String name;
  private String value;
  private String title;

  public BasicOption(String moduleIdx, int order, String name) {
    this(moduleIdx, order, name, null, null);
  }

  public BasicOption(String moduleIdx, int order, String name, String value, String title) {
    this.moduleIdx = moduleIdx;
    this.order = order;
    this.name = name;
    this.value = value;
    this.title = title;
  }
}
