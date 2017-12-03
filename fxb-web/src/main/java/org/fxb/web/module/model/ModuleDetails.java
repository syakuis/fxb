package org.fxb.web.module.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 모듈 정보를 여러 모듈들에 공유하기 위한 사용되는 모델
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 21.
 */
@Getter
@Setter
@ToString
public class ModuleDetails implements Module {
  private final String moduleIdx;
  private final String mid;
  private final String sid;
  private final String name;
  private String layout;
  private String menu;
  private String skin;
  private Map<String, Option> options;

  /**
   *
   * @param moduleIdx = moduleIdx (unique)
   * @param mid = moduleName
   * @param sid = moduleIdx (unique)
   * @param name = mdouleTitle
   */
  public ModuleDetails(String moduleIdx, String mid, String sid, String name) {
    this.moduleIdx = moduleIdx;
    this.mid = mid;
    this.sid = sid;
    this.name = name;
  }

  public void setOptions(Map<String, Option> options) {
    this.options = options;
  }

  public static Map<String, Option> setOptions(Option ...options) {
    return ModuleDetails.setOptions(Arrays.asList(options));
  }

  public static Map<String, Option> setOptions(List<Option> options) {
    Map<String, Option> result = new LinkedHashMap<>();
    for(Option option : options) {
      result.put(option.getName(), option);
    }

    return result;
  }
}
