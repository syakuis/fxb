package org.fxb.app.module.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.fxb.web.module.model.Option;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
@Getter
@Setter
@ToString
public class BasicModule {
  private String moduleIdx;
  private String moduleName;
  private String moduleId;
  private String browserTitle;
  private String skin;
  private String layoutIdx;
  private Date regDate;
  private List<Option> options;

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
