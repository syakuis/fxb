package org.fxb.boot.annotation;

import org.fxb.config.SecurityConfiguration;
import org.fxb.config.SecuritySupportConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 10. 6.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({ SecurityConfiguration.class, SecuritySupportConfiguration.class })
public @interface FxbSecurityBoot {
}
