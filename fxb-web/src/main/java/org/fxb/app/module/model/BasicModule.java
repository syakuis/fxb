package org.fxb.app.module.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.fxb.app.module.domain.Module;
import org.fxb.web.module.model.Option;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
@ToString
public class BasicModule extends Module {
  @Getter @Setter
  private List<Option> options;

  public BasicModule() {
    super();
  }

  public BasicModule(String moduleIdx, String moduleName, String moduleId, List<Option> options) {
    this(moduleIdx, moduleName, moduleId, new Date(), options);
  }

  public BasicModule(String moduleIdx, String moduleName, String moduleId, Date regDate, List<Option> options) {
    this.moduleIdx = moduleIdx;
    this.moduleName = moduleName;
    this.moduleId = moduleId;
    this.regDate = regDate;
    this.options = options;
  }

  public static List<Option> setOptions(Option...options) {
    return Arrays.asList(options);
  }
}
