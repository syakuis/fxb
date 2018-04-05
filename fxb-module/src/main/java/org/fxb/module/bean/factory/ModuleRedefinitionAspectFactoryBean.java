package org.fxb.module.bean.factory;

import org.fxb.module.Module;
import org.fxb.module.ModuleContextManager;
import org.fxb.module.ModuleContextService;
import org.fxb.module.ModuleRedefinition;
import org.fxb.module.aop.ModuleRedefinitionAspectAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link org.fxb.module.ModuleRedefinition} 주석을 가진 메서드가 호출될때 마다(변경될때 마다)
 * {@link ModuleContextManager#context} 를 재정의한다.
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 3. 28.
 * @see Module
 * @see ModuleContextManager
 * @see org.fxb.module.ModuleRedefinition
 */
public class ModuleRedefinitionAspectFactoryBean implements FactoryBean<Advisor> {
  private AspectJExpressionPointcutAdvisor advisor;
  private final ModuleContextManager moduleContextManager;
  private final ModuleContextService moduleContextService;

  public ModuleRedefinitionAspectFactoryBean(ModuleContextManager moduleContextManager,
      ModuleContextService moduleContextService) {
    this.moduleContextManager = moduleContextManager;
    this.moduleContextService = moduleContextService;
  }

  @Override
  public Advisor getObject() {
    if (advisor == null) {
      createObject();
    }
    return advisor;
  }

  private void createObject() {
    this.advisor = new AspectJExpressionPointcutAdvisor();
    advisor.setExpression("@annotation(" + ModuleRedefinition.class.getName() + ")");

    ModuleRedefinitionAspectAdvice advice = new ModuleRedefinitionAspectAdvice(moduleContextManager,
        moduleContextService);
    advisor.setAdvice(advice);
  }

  @Override
  public Class<Advisor> getObjectType() {
    return Advisor.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}