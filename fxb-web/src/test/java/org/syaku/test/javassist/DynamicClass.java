package org.syaku.test.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 24.
 */
public class DynamicClass {
  @Before
  public void init() throws Exception {
    ClassPool cp = ClassPool.getDefault();
    CtClass cc = cp.get("org.syaku.test.javassist.Hello");
    CtMethod m = cc.getDeclaredMethod("say");
    m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
    cc.toClass();
  }

  @Test
  public void test() {
    Hello hello = new Hello();
    hello.say();
  }
}
