package org.fxb.app.module.dao;

import org.fxb.app.module.domain.Module;
import org.fxb.app.module.dto.ModuleSearch;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
public interface ModuleDAO {
  List<Module> findAllByModuleName(String moduleName, ModuleSearch moduleSearch);
  List<Module> findAllByModuleNamePaging(
    String moduleName,
    int startRow,
    int endRow,
    ModuleSearch moduleSearch
  );
  long countByModuleName(String moduleName, ModuleSearch moduleSearch);
  Module findOneByModuleIdx(String moduleIdx);
  void insert(Module module);
  void update(Module module);
  void delete(String moduleIdx);
}
