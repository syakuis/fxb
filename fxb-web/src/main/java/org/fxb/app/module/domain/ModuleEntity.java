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
public class ModuleEntity {
  protected String moduleIdx;
  protected String moduleName;
  protected String moduleId;
  protected String browserTitle;
  protected String skin;
  protected String layoutIdx;
  protected Date regDate;

  protected List<ModuleOptionEntity> moduleOptions;
}
