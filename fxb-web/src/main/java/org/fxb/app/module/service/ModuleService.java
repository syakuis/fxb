package org.fxb.app.module.service;

import java.util.List;
import org.fxb.app.module.domain.Module;

/**
 * 캐싱 전략을 구성하기 위해 데이터를 변경할때는 moduleIdx 필드로 조회한다.
 * moduleContext 를 이용하면 다른 필드로 moduleIdx 를 얻을 수 있다.
 * todo ehcache -> redis
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public interface ModuleService {

  List<Module> getModules();

  List<Module> getModules(String moduleName);

  Module getModule(String moduleId);

  Module saveModule(Module module);

  /**
   * 모듈 정보를 저장한다. module.moduleIdx 존재하면 수정 없으면 등록한다.
   * @param module 모듈 정보
   * @param isOnlyNew 동일한 모듈정보가 존재여부와 상관없이 등록한다.
   * @return
   */
  Module saveModule(Module module, boolean isOnlyNew);

  void deleteModule(String moduleId);
}
