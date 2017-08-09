package org.fxb.module.service;

import java.util.LinkedList;
import java.util.List;
import org.fxb.module.ModuleDetailsService;
import org.fxb.module.model.Module;
import org.fxb.module.model.ModuleDetails;
import org.fxb.module.domain.ModuleEntity;


/**
 * ModuleEntity 의 데이터를 Module 에 저장한다.
 * todo init 으로 초기 실행만들기
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public class ModuleDetailsServiceImpl implements ModuleDetailsService {
  private ModuleService moduleService;

  public ModuleDetailsServiceImpl(ModuleService moduleService) {
    this.moduleService = moduleService;
  }

  private Module binding(ModuleEntity module) {
    return new ModuleDetails(module.getModuleName(), module.getModuleId());
  }

  @Override
  public List<Module> getModules() {
    List<ModuleEntity> modules = moduleService.getModules();
    List<Module> result = new LinkedList<>();
    for (ModuleEntity module : modules) {
      result.add(this.binding(module));
    }
    return result;
  }

  @Override
  public Module getModule(String moduleId) {
    ModuleEntity module = moduleService.getModule(moduleId);
    return this.binding(module);
  }
}

