package org.fxb.app.module.mapper;

import org.apache.ibatis.annotations.Param;
import org.fxb.app.module.dao.ModuleOptionDAO;
import org.fxb.app.module.domain.ModuleOptionEntity;
import org.fxb.config.support.Mapper;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Mapper
public interface ModuleOptionMapper extends ModuleOptionDAO {
  @Override
  List<ModuleOptionEntity> findByModuleIdx(@Param("moduleIdx") String moduleIdx);
  @Override
  void deleteByModuleOptionSrl(@Param("moduleIdx") String moduleIdx, @Param("moduleOptionSrl") Long moduleOptionSrl);
}
