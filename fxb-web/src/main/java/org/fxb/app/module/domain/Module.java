package org.fxb.app.module.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Getter
@Setter
@ToString
public class Module {
  protected String moduleIdx;
  protected String moduleName;
  protected String moduleId;
  protected String browserTitle;
  protected String skin;
  protected String layoutIdx;
  protected Date regDate;
}
