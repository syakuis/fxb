package org.fxb.app.module.service;

import org.fxb.app.module.domain.Module;
import org.fxb.web.module.ModuleContextManager;
import org.fxb.web.module.ModuleContextService;
import org.fxb.web.module.model.ModuleDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ModuleEntity 의 데이터를 Module 에 저장한다.
 * todo init 으로 초기 실행만들기
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
@Transactional(readOnly = true)
public class ModuleContextServiceImpl implements ModuleContextService {
  private ModuleContextManager moduleContextManager;
  private ModuleService moduleService;

  public void setModuleContextManager(ModuleContextManager moduleContextManager) {
    this.moduleContextManager = moduleContextManager;
  }

  public void setModuleService(ModuleService moduleService) {
    this.moduleService = moduleService;
  }

  @Override
  public void init() {
    List<Module> modules = moduleService.getModules();
    for (Module moduleEntity : modules) {
      org.fxb.web.module.model.Module module = new ModuleDetails(
        moduleEntity.getModuleName(),
        moduleEntity.getModuleId()
      );

      // todo layout skin menu

      // todo add moduleOption

      moduleContextManager.addModule(module);
    }
  }

  @Override
  public void setModule(String moduleName) {

  }
}
