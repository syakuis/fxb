package org.fxb.app.module.mybatis;

import org.apache.ibatis.annotations.Param;
import org.fxb.app.module.domain.ModuleEntity;
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
  List<ModuleEntity> select(@Param("moduleName") String moduleName, @Param("moduleSearch") ModuleSearch moduleSearch);
  List<ModuleEntity> selectPaging(
    @Param("moduleName") String moduleName,
    @Param("startRow") int startRow,
    @Param("endRow") int endRow,
    @Param("moduleSearch") ModuleSearch moduleSearch
  );
  long selectCount(@Param("moduleName") String moduleName, @Param("moduleSearch") ModuleSearch moduleSearch);
  ModuleEntity selectOne(@Param("moduleIdx") String moduleIdx);
  void insert(ModuleEntity module);
  void update(ModuleEntity module);
  void delete(@Param("moduleIdx") String moduleIdx);
}
