package org.fxb.app.module.domain;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
@ToString
public class ModuleOption {
  private String moduleId;
  private String name;
  private String value;
  private String title;
  private int order;
}