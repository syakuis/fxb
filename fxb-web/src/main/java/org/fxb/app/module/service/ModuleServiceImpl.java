package org.fxb.app.module.service;

import org.fxb.app.module.dao.ModuleDAO;
import org.fxb.app.module.dao.ModuleOptionBatchDAO;
import org.fxb.app.module.domain.Module;
import org.fxb.app.module.dto.ModuleSearch;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
public class ModuleServiceImpl implements ModuleService {
  private ModuleDAO moduleDAO;
  private ModuleOptionBatchDAO moduleOptionBatchDAO;

  public void setModuleDAO(ModuleDAO moduleDAO) {
    this.moduleDAO = moduleDAO;
  }

  public void setModuleOptionBatchDAO(ModuleOptionBatchDAO moduleOptionBatchDAO) {
    this.moduleOptionBatchDAO = moduleOptionBatchDAO;
  }

  @Override
  public List<Module> getModules(String moduleName) {
    Assert.notNull("the moduleName must not be null.", moduleName);
    // object 를 null 로 입력하면 myBatis 에서 매칭하지 못한다.
    return moduleDAO.findAllByModuleName(moduleName, new ModuleSearch());
  }

  @Override
  public List<Module> getModules() {
    return moduleDAO.findAllByModuleName(null, new ModuleSearch());
  }

  @Override
  public Module getModule(String moduleIdx) {
    Assert.notNull("the moduleIdx must not be null.", moduleIdx);
    return moduleDAO.findOneByModuleIdx(moduleIdx);
  }

  @Override
  public Module saveModule(Module module) {
    Assert.notNull(module, "the module must not be null");
    if (module.getModuleIdx() == null) {
      moduleDAO.insert(module);
    } else {
      moduleDAO.update(module);
    }

    module.setModuleOptions(
      moduleOptionBatchDAO.save(module.getModuleIdx(), module.getModuleOptions(), true)
    );

    return module;
  }

  @Override
  public void deleteModule(String moduleIdx) {
    moduleDAO.delete(moduleIdx);
  }
}
