package org.fxb.app.module.dao;

import org.fxb.app.module.domain.ModuleEntity;
import org.fxb.app.module.dto.ModuleSearch;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
public interface ModuleDAO {
  List<ModuleEntity> findAllByModuleName(String moduleName, ModuleSearch moduleSearch);
  List<ModuleEntity> findAllByModuleNamePaging(
    String moduleName,
    int startRow,
    int endRow,
    ModuleSearch moduleSearch
  );
  long countByModuleName(String moduleName, ModuleSearch moduleSearch);
  ModuleEntity findOneByModuleIdx(String moduleIdx);
  void insert(ModuleEntity module);
  void update(ModuleEntity module);
  void delete(String moduleIdx);
}
