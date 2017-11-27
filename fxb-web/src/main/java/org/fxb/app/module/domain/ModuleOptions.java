package org.fxb.app.module.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.fxb.web.module.model.Option;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
@Getter
@Setter
@ToString
public class ModuleOptions {
  protected String moduleIdx;
  protected int order;
  protected String name;
  protected String value;
  protected String title;
}
