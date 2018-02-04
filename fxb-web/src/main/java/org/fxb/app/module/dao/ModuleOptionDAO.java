package org.fxb.app.module.dao;

import org.fxb.app.module.domain.ModuleOption;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
public interface ModuleOptionDAO {
  List<ModuleOption> findByModuleId(String moduleId);

  void insert(ModuleOption moduleOption);

  void updateByModuleIdAndOptionName(String moduleId, String optionName, ModuleOption moduleOption);

  void deleteByModuleId(String moduleId);

  void deleteByModuleIdAndOptionName(String moduleId, String optionName);
}
