package org.fxb.module.service;

import java.util.List;
import org.fxb.module.domain.ModuleEntity;

/**
 * 캐싱 전략을 구성하기 위해 데이터를 변경할때는 moduleIdx 필드로 조회한다.
 * moduleContext 를 이용하면 다른 필드로 moduleIdx 를 얻을 수 있다.
 * todo ehcache -> redis
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public interface ModuleService {

  List<ModuleEntity> getModules();

  List<ModuleEntity> getModules(String moduleName);

  ModuleEntity getModule(String moduleId);

  ModuleEntity saveModule(ModuleEntity module);

  /**
   * 모듈 정보를 저장한다. module.moduleIdx 존재하면 수정 없으면 등록한다.
   * @param module 모듈 정보
   * @param isOnlyNew 동일한 모듈정보가 존재여부와 상관없이 등록한다.
   * @return
   */
  ModuleEntity saveModule(ModuleEntity module, boolean isOnlyNew);

  void deleteModule(String moduleId);
}
