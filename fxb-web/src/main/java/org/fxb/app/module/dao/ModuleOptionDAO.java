package org.fxb.app.module.dao;

import org.fxb.app.module.domain.ModuleOption;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
public interface ModuleOptionDAO {
  List<ModuleOption> findByModuleIdx(String moduleIdx);

  void insert(ModuleOption moduleOption);

  void update(ModuleOption moduleOption);

  void deleteByModuleIdx(String moduleIdx);

  void deleteByModuleOptionSrl(String moduleIdx, Long moduleOptionSrl);
}
