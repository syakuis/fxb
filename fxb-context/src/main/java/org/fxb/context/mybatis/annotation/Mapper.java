package org.fxb.context.mybatis.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 2.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Mapper {
  /**
   * The value may indicate a suggestion for a logical mapper name, to be turned into a Spring bean in case of an autodetected component.
   *
   * @return the suggested mapper name, if any
   */
  String value() default "";
}