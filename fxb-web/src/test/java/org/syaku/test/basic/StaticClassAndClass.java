package org.syaku.test.basic;

import org.junit.Test;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 13.
 */
public class StaticClassAndClass {
  class BasicClass {
    public BasicClass() {
      System.out.println("member class ...");
    }

    public void invoker() {
      System.out.println("member class invoker ...");
    }
  }
  static class StaticClass {
    public StaticClass() {
      System.out.println("static class ...");
    }

    public void invoker() {
      System.out.println("static class invoker ...");
    }
  }
}
