package org.fxb.app.module.service;

import org.fxb.web.module.ModuleDetailsService;
import org.fxb.web.module.model.Module;
import org.fxb.web.module.model.ModuleDetails;

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

  @Override
  public Module getModule(String moduleId) {
    org.fxb.app.module.domain.Module module = moduleService.getModule(moduleId);
    return new ModuleDetails(module.getModuleName(), module.getModuleId());
  }
}

