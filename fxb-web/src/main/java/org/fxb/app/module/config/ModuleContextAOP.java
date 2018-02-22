package org.fxb.app.module.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 22.
 */
@Aspect
public class ModuleContextAOP {
  private final Logger logger = LoggerFactory.getLogger(ModuleContextAOP.class);

  @AfterReturning("@annotation(org.fxb.app.module.config.ModuleSync)")
  public void sync(JoinPoint joinPoint) {
    logger.debug("----------------------------- aspect");
    System.out.println(joinPoint.getArgs());
  }
}
