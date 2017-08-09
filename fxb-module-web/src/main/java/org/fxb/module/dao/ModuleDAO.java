package org.fxb.module.dao;

import java.util.List;
import org.fxb.module.domain.ModuleEntity;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
public interface ModuleDAO {
  List<ModuleEntity> findAll();
  List<ModuleEntity> findByModuleName(String moduleName);
  ModuleEntity findOneByModuleId(String moduleId);
  void insert(ModuleEntity module);
  void deleteByModuleId(String moduleId);
}
