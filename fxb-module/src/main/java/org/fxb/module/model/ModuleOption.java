package org.fxb.module.model;

/**
 * 모듈에서 사용되는 부가 정보들을 담는 다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
public interface ModuleOption {
  /**
   * 옵션을 찾기 위해 사용되는 이름
   * @return
   */
  String getName();

  /**
   * 옵션 순서
   * @return
   */
  int getOrder();

  /**
   * 옵션 값이며 json 을 문자형으로 저장된다.
   * @return
   */
  String getValue();

  /**
   * 옵션을 명확하게 알 수있는 이름
   * @return
   */
  String getTitle();
}
