package org.syaku.test.basic;

import org.junit.Test;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 13.
 */
public class StaticClassAndClassTest {

  @Test
  public void test() {
    // static class 클래스 는 StaticClassAndClass 인스턴스하지 않고 정적 클래스를 인스턴스할 수 있다.
    new StaticClassAndClass.StaticClass().invoker();
    new StaticClassAndClass().new BasicClass().invoker();
  }
}
