package org.fxb.module.dao;

import java.util.List;
import org.fxb.module.domain.ModuleOptionEntity;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
public interface ModuleOptionDAO {
  List<ModuleOptionEntity> findByModuleId(String moduleId);

  void insert(ModuleOptionEntity moduleOption);

  void updateByModuleIdAndOptionName(String moduleId, String optionName, ModuleOptionEntity moduleOption);

  void deleteByModuleId(String moduleId);

  void deleteByModuleIdAndOptionName(String moduleId, String optionName);
}
