package org.fxb.module.data.dao;

import org.fxb.module.data.domain.ModuleOptionEntity;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 12.
 */
public interface ModuleOptionDAO {
  void save(ModuleOptionEntity moduleOptionEntity);
  void deleteByModuleId(String moduleId);
  void deleteOne(String moduleId, String optionName);
}