package org.fxb.app.module.mapper;

import org.apache.ibatis.annotations.Param;
import org.fxb.app.module.dao.ModuleDAO;
import org.fxb.app.module.domain.Module;
import org.fxb.config.support.Mapper;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Mapper("moduleDAO")
public interface ModuleMapper extends ModuleDAO {
  @Override
  List<Module> findByModuleName(@Param("moduleName") String moduleName);
  @Override
  Module findOneByModuleId(@Param("moduleId") String moduleId);
  @Override
  void deleteByModuleId(@Param("moduleId") String moduleId);
}