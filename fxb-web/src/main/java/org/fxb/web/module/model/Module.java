package org.fxb.web.module.model;

/**
 * 모든 모듈은 모듈 정보를 이용하여 작동된다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
public interface Module {
  /**
   * ModuleContextManager 에 저장될때 생성되는 고유값
   * @return
   */
  String getModuleIdx();

  /**
   * 모듈 이름이며 같은 이름인 경우 같은 모듈로 판단한다.
   * @return
   */
  String getModuleName();

  /**
   * 모듈의 고유 이름이며 중복될 수 없다. 같은 이름이 생성될때 moduleIdx 를 비교해서 생성여부를 결정한다.
   * @return
   */
  String getModuleId();

  /**
   * 모듈을 명확하게 알 수 있는 이름
   * @return
   */
  String getTitle();

  /**
   * ModuleContextManager 에 저장되는 날짜.
   * @return
   */
  java.util.Date getCreatedDate();

  /**
   * 모듈에서 사용되는 부가 정보들
   * @return
   */
  java.util.Map<String, ModuleOption> getModuleOptions();
}
