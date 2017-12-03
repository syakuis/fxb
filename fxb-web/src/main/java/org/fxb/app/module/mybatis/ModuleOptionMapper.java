package org.fxb.app.module.mybatis;

import org.apache.ibatis.annotations.Param;
import org.fxb.app.module.domain.ModuleOptionEntity;
import org.fxb.config.support.Mapper;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Mapper
public interface ModuleOptionMapper {
  List<ModuleOptionEntity> selectByModuleIdx(@Param("moduleIdx") String moduleIdx);
  void insert(ModuleOptionEntity moduleOption);
  void update(ModuleOptionEntity moduleOption);
  void deleteByModuleIdx(String moduleIdx);
  void deleteByModuleOptionSrl(@Param("moduleIdx") String moduleIdx, @Param("moduleOptionSrl") Long moduleOptionSrl);
}
