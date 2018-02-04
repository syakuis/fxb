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
  private final String moduleName;
  private final String moduleId;
  private String title;
  private Date createdDate;
  private Map<String, ModuleOption> moduleOptions;

  public ModuleDetails(String moduleName, String moduleId) {
    this(null, moduleName, moduleId);
  }

  public ModuleDetails(String moduleIdx, String moduleName, String moduleId) {
    if (moduleIdx == null) {
      this.moduleIdx = UUID.randomUUID().toString();
    } else {
      this.moduleIdx = moduleIdx;
    }
    this.moduleName = moduleName;
    this.moduleId = moduleId;
    this.createdDate = new Date();
  }

  public static Map<String, ModuleOption> setModuleOptions(ModuleOption...options) {
    return ModuleDetails.setModuleOptions(Arrays.asList(options));
  }

  public static Map<String, ModuleOption> setModuleOptions(List<ModuleOption> options) {
    Map<String, ModuleOption> result = new LinkedHashMap<>();
    for(ModuleOption option : options) {
      result.put(option.getName(), option);
    }

    return result;
  }
}
