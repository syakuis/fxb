package org.fxb.module;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Module 을 재정의하기 위한 주석.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 14.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModuleRedefinition {
  String value() default "";
  String expression() default "";
  Scope scope() default Scope.THIS;
  Mode mode() default Mode.REFRESH;

  enum Mode {
    REFRESH, REMOVE
  }

  enum Scope {
    THIS, ALL
  }
}
