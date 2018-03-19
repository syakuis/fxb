package org.fxb.module.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.fxb.module.ModuleContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 22.
 */
@Aspect
@Component
public class ModuleContextAOP {
  private ModuleContextService moduleContextService;

  @Autowired
  public ModuleContextAOP(@Qualifier("moduleContextService") ModuleContextService moduleContextService) {
    Assert.notNull(moduleContextService, "The class must not be null");
    this.moduleContextService = moduleContextService;
  }

  @AfterReturning("@annotation(org.fxb.module.annotation.ModuleSync)")
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
