package org.fxb.app.module.mapper;

import org.apache.ibatis.annotations.Param;
import org.fxb.app.module.dao.ModuleDAO;
import org.fxb.app.module.domain.ModuleEntity;
import org.fxb.app.module.dto.ModuleSearch;
import org.fxb.config.support.Mapper;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Mapper
public interface ModuleMapper extends ModuleDAO {
  @Override
  List<ModuleEntity> findAllByModuleName(@Param("moduleName") String moduleName, @Param("moduleSearch") ModuleSearch moduleSearch);
  @Override
  List<ModuleEntity> findAllByModuleNamePaging(
    @Param("moduleName") String moduleName,
    @Param("startRow") int startRow,
    @Param("endRow") int endRow,
    @Param("moduleSearch") ModuleSearch moduleSearch
  );
  @Override
  long countByModuleName(@Param("moduleName") String moduleName, @Param("moduleSearch") ModuleSearch moduleSearch);
  @Override
  ModuleEntity findOneByModuleIdx(@Param("moduleIdx") String moduleIdx);
  @Override
  void delete(@Param("moduleIdx") String moduleIdx);
}