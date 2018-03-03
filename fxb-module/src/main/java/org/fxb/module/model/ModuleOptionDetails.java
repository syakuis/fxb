package org.fxb.module.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 12. 1.
 */
@Getter
@EqualsAndHashCode
public class ModuleOptionDetails implements ModuleOption {
  private final String name;
  private String value;
  private String title;
  private int order;

  public ModuleOptionDetails(String name) {
    this(name, null, null, 0);
  }

  public ModuleOptionDetails(String name, String value, int order) {
    this(name, value, null, order);
  }

  public ModuleOptionDetails(String name, String value, String title, int order) {
    this.name = name;
    this.value = value;
    this.title = title;
    this.order = order;
  }
}
