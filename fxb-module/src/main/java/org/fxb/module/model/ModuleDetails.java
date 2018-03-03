package org.fxb.module.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 모듈 정보를 여러 모듈들에 공유하기 위한 사용되는 모델
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 21.
 */
@Getter
@ToString
public class ModuleDetails implements Module, Serializable {
  private static final long serialVersionUID = 2098116822975147890L;
  private final String moduleName;
  private final String moduleId;
  private String moduleIdx;
  private Date createdDate;

  @Setter
  private String title;
  @Setter
  private Map<String, ModuleOption> moduleOptions;

  public ModuleDetails(String moduleName, String moduleId) {
    this.moduleIdx = UUID.randomUUID().toString();
    this.moduleName = moduleName;
    this.moduleId = moduleId;
    this.createdDate = new Date();
  }

  public static Map<String, ModuleOption> setModuleOptions(ModuleOption... options) {
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
