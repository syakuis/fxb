package org.fxb.app.module.service;

import java.util.List;
import org.fxb.app.module.config.ModuleSync;
import org.fxb.app.module.config.ModuleSyncType;
import org.fxb.app.module.dao.ModuleDAO;
import org.fxb.app.module.domain.Module;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Transactional(readOnly = true)
public class ModuleServiceImpl implements ModuleService {
  private ModuleDAO moduleDAO;

  public void setModuleDAO(ModuleDAO moduleDAO) {
    this.moduleDAO = moduleDAO;
  }

  @Override
  public List<Module> getModules(String moduleName) {
    Assert.notNull("the moduleName must not be null.", moduleName);
    // object 를 null 로 입력하면 myBatis 에서 매칭하지 못한다.
    return moduleDAO.findByModuleName(moduleName);
  }

  @Override
  public List<Module> getModules() {
    return moduleDAO.findAll();
  }

  @Override
  public Module getModule(String moduleId) {
    Assert.notNull("the moduleIdx must not be null.", moduleId);
    return moduleDAO.findOneByModuleId(moduleId);
  }

  @Override
  @Transactional
  @ModuleSync("#{args[0].moduleId}")
  public Module saveModule(Module module) {
    return saveModule(module, true);
  }

  @Override
  @Transactional
  @ModuleSync("#{args[0].moduleId}")
  public Module saveModule(Module module, boolean isOnlyNew) {
    Assert.notNull(module, "the module must not be null");

    Module _module = isOnlyNew ? null : moduleDAO.findOneByModuleId(module.getModuleId());

    if (_module == null && isOnlyNew) {
      moduleDAO.insert(module);
      return module;
    }

    // todo moduleOption insert & update

    return _module;
  }

  @Override
  @Transactional
  @ModuleSync(value = "#{module.moduleId}", type = ModuleSyncType.DELETE)
  public void deleteModule(String moduleId) {
    moduleDAO.deleteByModuleId(moduleId);

    // todo moduleOption delete
  }
}
