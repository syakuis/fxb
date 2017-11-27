package org.fxb.app.module.domain.condition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Getter
@Setter
@ToString
public class ModuleSearch {
  private String searchType;
  private String searchValue;
}
