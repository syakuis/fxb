package org.fxb.web.module;

/**
 * DB 에서 모듈 정보를 읽어 온다. 즉 가변 모듈 정보를 관리한다.
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public interface ModuleContextService {

  /**
   * 최초 로드될때 호출된다.
   */
  void init();

  /**
   * 모듈이 변경되면 갱신하기 위해 호출된다.
   * 변경된 모듈은 moduleName 모두가 갱신된다.
   * @param moduleName
   */
  void getModule(String moduleName);
}
