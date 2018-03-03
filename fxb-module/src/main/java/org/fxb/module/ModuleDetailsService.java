package org.fxb.module;

import java.util.List;
import org.fxb.module.model.Module;

/**
 * DB 에서 모듈 정보를 읽어 온다. 즉 가변 모듈 정보를 관리한다.
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public interface ModuleDetailsService {
  List<Module> getModules();
  Module getModule(String moduleId);
}
