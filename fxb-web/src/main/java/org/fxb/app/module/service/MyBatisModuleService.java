package org.fxb.app.module.service;

import org.fxb.app.module.domain.Module;
import org.fxb.app.module.domain.condition.ModuleSearch;
import org.fxb.app.module.mapper.ModuleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Service
@Transactional(readOnly = true)
public class MyBatisModuleService {
  @Resource(name = "moduleMapper")
  private ModuleMapper moduleMapper;

  public List<Module> getModules(String moduleName) {
    // object 를 null 로 입력하면 myBatis 에서 매칭하지 못한다.
    return moduleMapper.select(moduleName, new ModuleSearch());
  }

  public Module getModule(String moduleIdx, String moduleId) {
    return moduleMapper.selectOne(moduleIdx, moduleId);
  }
//
//  public List<Module> getModuleListAndPaging() {
//    return null;
//  }
//
//  public Module getModuleView() {
//    return null;
//  }

  @Transactional
  public void saveModule(Module module) {
    if (module != null && module.getModuleIdx() != null) {
      moduleMapper.update(module);
    } else {
      moduleMapper.insert(module);
    }
  }

  @Transactional
  public void deleteModule(String moduleIdx) {
    moduleMapper.delete(moduleIdx);
  }
}
