package org.fxb.app.module.service;

import org.fxb.app.module.domain.Module;
import org.fxb.app.module.domain.ModuleOption;
import org.fxb.web.module.BasicModuleContextService;
import org.fxb.web.module.model.ModuleDetails;
import org.fxb.web.module.model.Option;
import org.fxb.web.module.model.OptionDetails;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ModuleEntity 의 데이터를 Module 에 저장한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
@Transactional(readOnly = true)
public class ModuleContextServiceImpl extends BasicModuleContextService {
  ModuleService moduleService;

  ModuleOptionService moduleOptionService;

  public void setModuleService(ModuleService moduleService) {
    this.moduleService = moduleService;
  }

  public void setModuleOptionService(ModuleOptionService moduleOptionService) {
    this.moduleOptionService = moduleOptionService;
  }

  @Override
  @Cacheable(cacheNames = "fxb.module", key = "'moduleContext'", sync = true)
  public Map<String, org.fxb.web.module.model.Module> getModuleContext() {
    Map<String, org.fxb.web.module.model.Module> result = new HashMap<>();

    List<Module> modules = moduleService.getModules();
    for (Module moduleEntity : modules) {
      org.fxb.web.module.model.Module module = new ModuleDetails(
        moduleEntity.getModuleIdx(),
        moduleEntity.getModuleName(),
        moduleEntity.getModuleId(),
        moduleEntity.getBrowserTitle()
      );
      // menuIdx = menu
      // layoutIdx = layout
      module.setSkin(moduleEntity.getSkin());

      List<Option> options = new LinkedList<>();

      List<ModuleOption> moduleOptions = moduleOptionService.getModuleOptions(moduleEntity.getModuleIdx());
      for (ModuleOption moduleOption : moduleOptions) {
        Option option = new OptionDetails(
          moduleOption.getName(),
          moduleOption.getValue(),
          moduleOption.getTitle(),
          moduleOption.getOrder()

        );
        options.add(option);
      }
      module.setOptions(ModuleDetails.setOptions(options));

      result.put(module.getModuleIdx(), module);
    }

    return result;
  }
}
