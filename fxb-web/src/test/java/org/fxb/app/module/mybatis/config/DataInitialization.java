package org.fxb.app.module.mybatis.config;

import org.fxb.app.module.domain.ModuleEntity;
import org.fxb.app.module.domain.ModuleOptionEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 28.
 */
public class DataInitialization {
  public static ModuleEntity module(String moduleName, String moduleId, boolean isNew) {
    ModuleEntity module = new ModuleEntity();
    module.setModuleName(moduleName);
    if (isNew) {
      module.setModuleId(moduleId + new Date());
    } else {
      module.setModuleId(moduleId);
    }
    module.setBrowserTitle(moduleName + "_" + moduleId);
    module.setRegDate(new Date());

    return module;
  }

  public static List<ModuleOptionEntity> moduleOptions(String moduleIdx, int count) {
    List<ModuleOptionEntity> moduleOptions = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      ModuleOptionEntity moduleOption = new ModuleOptionEntity();
      moduleOption.setModuleIdx(moduleIdx);
      moduleOption.setOrder(i);
      moduleOption.setName("name_" + i);
      moduleOption.setValue("value_" + i);
      moduleOption.setTitle("title_" + i);

      moduleOptions.add(moduleOption);
    }

    return moduleOptions;
  }
}
