package org.fxb.app.module.service;

import org.fxb.app.module.domain.Module;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 캐싱 전략을 구성하기 위해 데이터를 변경할때는 moduleIdx 필드로 조회한다.
 * moduleContext 를 이용하면 다른 필드로 moduleIdx 를 얻을 수 있다.
 * todo ehcache -> redis
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
@Transactional(readOnly = true)
public interface ModuleService {
  List<Module> getModules();

  List<Module> getModules(String moduleName);

  Module getModule(String moduleId);

  @Transactional
  Module saveModule(Module module);
  @Transactional
  Module saveModule(Module module, boolean isOnlyNew);

  @Transactional
  void deleteModule(String moduleId);
}
