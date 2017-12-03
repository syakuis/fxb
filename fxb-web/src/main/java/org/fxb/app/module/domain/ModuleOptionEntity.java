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
public class ModuleOptionEntity {
  protected String moduleIdx;
  // todo 왜 Long?
  protected Long moduleOptionSrl;
  protected int order;
  protected String name;
  protected String value;
  protected String title;
}