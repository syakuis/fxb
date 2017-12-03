package org.fxb.app.module.component.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 12. 1.
 */
@Component
public class ModuleGenerator implements KeyGenerator {
  private static final Logger logger = LoggerFactory.getLogger(ModuleGenerator.class);

  @Override
  public Object generate(Object target, Method method, Object... params) {
    logger.debug("{}");
    return null;
  }
}
