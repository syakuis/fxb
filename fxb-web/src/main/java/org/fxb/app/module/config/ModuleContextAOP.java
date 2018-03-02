package org.fxb.app.module.config;

import java.lang.annotation.Annotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.fxb.web.module.ModuleContextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 22.
 */
@Aspect
public class ModuleContextAOP {
  private final Logger logger = LoggerFactory.getLogger(ModuleContextAOP.class);

  private ModuleContextService moduleContextService;

  public ModuleContextAOP(@Qualifier("moduleContextService") ModuleContextService moduleContextService) {
    Assert.notNull(moduleContextService, "The class must not be null");
    this.moduleContextService = moduleContextService;
  }

  @AfterReturning("@annotation(org.fxb.app.module.config.ModuleSync)")
  public void sync(JoinPoint joinPoint) {
    MethodSignature invocation = (MethodSignature) joinPoint.getSignature();
    invocation.getMethod().getDeclaredAnnotations();

    moduleContextService.sync();

//    Annotation[] annotations = clazz.getAnnotations();
//    for (Annotation annotation : annotations) {
//      System.out.println(annotation.toString());
//    }
//    Object[] objects = joinPoint.getArgs();
//
//    for (Object object : objects) {
//      System.out.println(object.toString());
//    }
  }
}
