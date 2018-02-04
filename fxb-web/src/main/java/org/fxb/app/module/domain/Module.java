package org.fxb.app.module.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Module {
  private String moduleId;
  private String moduleName;
  private Date regDate;

  private List<ModuleOption> moduleOptions;
}
