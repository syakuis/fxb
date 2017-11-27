package org.fxb.app.module.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.fxb.app.module.domain.ModuleOptions;
import org.fxb.web.module.model.Option;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
@Getter
@Setter
@ToString
public class BasicOption extends ModuleOptions implements Option {
  public BasicOption() {
    super();
  }
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
