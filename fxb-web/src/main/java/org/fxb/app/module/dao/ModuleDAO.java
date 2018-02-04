package org.fxb.app.module.dao;

import org.fxb.app.module.domain.Module;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
public interface ModuleDAO {
  List<Module> findAll();
  List<Module> findByModuleName(String moduleId);
  Module findOneByModuleId(String moduleId);
  void insert(Module module);
  void deleteByModuleId(String moduleId);
}
