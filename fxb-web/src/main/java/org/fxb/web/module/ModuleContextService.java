package org.fxb.web.module;

import org.fxb.web.module.model.Module;

import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public interface ModuleContextService {
  Map<String, Module> getModuleContext();
}
