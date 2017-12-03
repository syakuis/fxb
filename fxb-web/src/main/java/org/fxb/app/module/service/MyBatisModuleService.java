package org.fxb.app.module.service;

import org.fxb.app.module.domain.ModuleEntity;
import org.fxb.app.module.domain.ModuleOptionEntity;
import org.fxb.app.module.domain.condition.ModuleSearch;
import org.fxb.app.module.mybatis.ModuleMapper;
import org.fxb.app.module.mybatis.ModuleOptionBatchDAO;
import org.fxb.app.module.mybatis.ModuleOptionMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Service
public class MyBatisModuleService implements ModuleService {
  @Resource(name = "moduleMapper")
  private ModuleMapper moduleMapper;

  @Resource(name = "moduleOptionMapper")
  private ModuleOptionMapper moduleOptionMapper;

  @Resource(name = "moduleOptionBatchDAO")
  private ModuleOptionBatchDAO moduleOptionBatchDAO;

  @Override
  public List<ModuleEntity> getModules(String moduleName) {
    Assert.notNull("the moduleName must not be null.", moduleName);
    // object 를 null 로 입력하면 myBatis 에서 매칭하지 못한다.
    return moduleMapper.select(moduleName, new ModuleSearch());
  }

  @Override
  public List<ModuleEntity> getModules() {
    return moduleMapper.select(null, new ModuleSearch());
  }

  @Override
  public ModuleEntity getModule(String moduleIdx) {
    Assert.notNull("the moduleIdx must not be null.", moduleIdx);
    return moduleMapper.selectOne(moduleIdx);
  }

  @Override
  public ModuleEntity saveModule(ModuleEntity module) {
    Assert.notNull(module, "the module must not be null");
    if (module.getModuleIdx() == null) {
      moduleMapper.insert(module);
    } else {
      moduleMapper.update(module);
    }

    module.setModuleOptions(
      moduleOptionBatchDAO.save(module.getModuleIdx(), module.getModuleOptions(), true)
    );

    return module;
  }

  @Override
  public void deleteModule(String moduleIdx) {
    moduleMapper.delete(moduleIdx);
  }
}
