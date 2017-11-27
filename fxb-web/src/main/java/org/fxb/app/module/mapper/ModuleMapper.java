package org.fxb.app.module.mapper;

import org.apache.ibatis.annotations.Param;
import org.fxb.app.module.domain.Module;
import org.fxb.app.module.domain.condition.ModuleSearch;
import org.fxb.config.support.Mapper;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Mapper
public interface ModuleMapper {
  List<Module> select(@Param("moduleName") String moduleName, @Param("moduleSearch") ModuleSearch moduleSearch);
  List<Module> selectPaging(@Param("moduleName") String moduleName, @Param("startRow") int startRow, @Param("endRow") int endRow, @Param("moduleSearch") ModuleSearch moduleSearch);
  long selectCount(@Param("moduleName") String moduleName, @Param("moduleSearch") ModuleSearch moduleSearch);
  Module selectOne(@Param("moduleIdx") String moduleIdx, @Param("moduleId") String moduleId);
  void insert(Module module);
  void update(Module module);
  void delete(String moduleIdx);
}
