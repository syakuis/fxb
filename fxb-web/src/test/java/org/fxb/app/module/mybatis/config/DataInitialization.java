package org.fxb.app.module.mybatis.config;

import org.fxb.app.module.domain.Module;
import org.fxb.app.module.domain.ModuleOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 28.
 */
public class DataInitialization {
  private static final Logger logger = LoggerFactory.getLogger(DataInitialization.class);

  public static Module module(String moduleName, String moduleId, boolean isNew) {
    Module module = new Module();
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

  public static List<ModuleOptions> moduleOptions(String moduleIdx, int count) {
    List<ModuleOptions> moduleOptions = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      ModuleOptions options = new ModuleOptions();
      options.setModuleIdx(moduleIdx);
      options.setOrder(i);
      options.setName("name_" + i);
      options.setValue("value_" + i);
      options.setTitle("title_" + i);

      moduleOptions.add(options);
    }

    return moduleOptions;
  }
}
