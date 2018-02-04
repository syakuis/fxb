package org.fxb.app.module.mapper;

import org.apache.ibatis.annotations.Param;
import org.fxb.app.module.dao.ModuleOptionDAO;
import org.fxb.app.module.domain.ModuleOption;
import org.fxb.config.support.Mapper;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Mapper("moduleOptionDAO")
public interface ModuleOptionMapper extends ModuleOptionDAO {
  @Override
  List<ModuleOption> findByModuleId(@Param("moduleId") String moduleId);

  @Override
  void insert(ModuleOption moduleOption);

  @Override
  void updateByModuleIdAndOptionName(
    @Param("moduleId") String moduleId,
    @Param("optionName") String optionName,
    @Param("moduleOption") ModuleOption moduleOption);

  @Override
  void deleteByModuleId(@Param("moduleId") String moduleId);

  @Override
  void deleteByModuleIdAndOptionName(
    @Param("moduleId") String moduleId, @Param("optionName") String optionName);
}
