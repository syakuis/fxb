package org.fxb.module;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 모듈 정의 주석.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 1. 14.
 * @see org.fxb.module.bean.factory.ModuleContextManagerFactoryBean
 * @see ModuleDefinitionScanner
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModuleDefinition {
}
