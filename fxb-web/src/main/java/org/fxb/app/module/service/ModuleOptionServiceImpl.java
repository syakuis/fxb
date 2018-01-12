package org.fxb.app.module.service;

import org.fxb.app.module.dao.ModuleOptionDAO;
import org.fxb.app.module.domain.ModuleOptionEntity;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 12. 1.
 */
public class ModuleOptionServiceImpl implements ModuleOptionService {
  private ModuleOptionDAO moduleOptionDAO;

  public void setModuleOptionDAO(ModuleOptionDAO moduleOptionDAO) {
    this.moduleOptionDAO = moduleOptionDAO;
  }

  @Override
  public List<ModuleOptionEntity> getModuleOptions(String moduleIdx) {
    return moduleOptionDAO.findByModuleIdx(moduleIdx);
  }
}
