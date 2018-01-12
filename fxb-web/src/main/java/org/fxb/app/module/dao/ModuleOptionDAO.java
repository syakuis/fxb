package org.fxb.app.module.dao;

import org.fxb.app.module.domain.ModuleOptionEntity;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
public interface ModuleOptionDAO {
  List<ModuleOptionEntity> findByModuleIdx(String moduleIdx);

  void insert(ModuleOptionEntity moduleOption);

  void update(ModuleOptionEntity moduleOption);

  void deleteByModuleIdx(String moduleIdx);

  void deleteByModuleOptionSrl(String moduleIdx, Long moduleOptionSrl);
}
