package org.fxb.module;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 2.
 */
public interface ModuleContextService {
  List<Module> getModules();
  Module getModule(String moduleId);
}
