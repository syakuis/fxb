package org.fxb.module.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.fxb.module.Module;
import org.fxb.module.ModuleContextManager;
import org.fxb.module.ModuleContextService;
import org.fxb.module.ModuleRedefinition;
import org.fxb.module.ModuleRedefinition.Mode;
import org.fxb.module.ModuleRedefinition.Scope;
import org.fxb.module.bean.factory.ModuleRedefinitionAspectFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;

/**
 * {@link ModuleRedefinitionAspectFactoryBean#createObject()} 를 위한 Aspect Advice.
 * {@link ModuleContextService}를 통해 갱신되 데이터를 얻어 {@link ModuleContextManager#context}에 재정의한다.
 * 이때 {@link org.fxb.module.Module} 가변인 데이터는 제거되고, 불변인 데이터를 수정할 수 없다.
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 3.
 * @see ModuleRedefinitionAspectFactoryBean
 * @see ModuleContextManager
 * @see ModuleContextService
 */
public class ModuleRedefinitionAspectAdvice implements AfterReturningAdvice {
  private final Logger logger = LoggerFactory.getLogger(ModuleRedefinitionAspectAdvice.class);

  private final ModuleContextManager moduleContextManager;
  private final ModuleContextService moduleContextService;

  public ModuleRedefinitionAspectAdvice(ModuleContextManager moduleContextManager,
      ModuleContextService moduleContextService) {
    Assert.notNull(moduleContextManager, "the moduleContextManager must not be null.");
    Assert.notNull(moduleContextService, "the moduleContextService must not be null.");
    this.moduleContextManager = moduleContextManager;
    this.moduleContextService = moduleContextService;
  }

  @Override
  public void afterReturning(Object result, Method method, Object[] args, Object target) {
    ModuleRedefinition moduleRedefinition = method.getAnnotation(ModuleRedefinition.class);

    Mode mode = moduleRedefinition.mode();
    String expression = moduleRedefinition.expression();

    Map<String, Object> context = new HashMap<>();
    context.put("result", result);
    context.put("args", args);

    StandardEvaluationContext evaluationContext = new StandardEvaluationContext(context);
    evaluationContext.addPropertyAccessor(new MapAccessor());

    ExpressionParser parser = new SpelExpressionParser();
    // todo spel injection attach!!!
    String moduleId = parser.parseExpression(expression).getValue(evaluationContext, String.class);

    if (logger.isDebugEnabled()) {
      logger.debug("afterReturning - mode: {}, moduleId: {}", mode, moduleId);
    }

    Assert.hasText(moduleId, "afterReturning - moduleId must not be empty");

    if (mode.equals(Mode.REFRESH)) {
      moduleContextManager.add(moduleContextService.getModule(moduleId));
    } else if (mode.equals(Mode.REMOVE)) {
      moduleContextManager.remove(moduleId);
    }
  }
}
