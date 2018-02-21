package org.fxb.app.module.domain;

import java.util.Date;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Module {
  private String moduleId;
  private String moduleName;
  private Date regDate;

  private List<ModuleOption> moduleOptions;

  public Module(String moduleId, String moduleName, Date regDate) {
    this.moduleId = moduleId;
    this.moduleName = moduleName;
    this.regDate = regDate;
  }
}
