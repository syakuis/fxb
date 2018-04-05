package org.spring.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AspectConfiguration.class)
public class JavaBasedAspectConfigurationTest {

  @Autowired Employ employ;

  @Test
  public void test() {
    employ.nameUpdate("good");
  }
}

@Configuration
@EnableAspectJAutoProxy
class AspectConfiguration {

  @Bean
  public Employ employ() {
    return new Employ();
  }

  @Bean
  public Advisor basicAdvisor() {
    AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
    advisor.setExpression("@annotation(org.spring.aop.TargetAOP)");
    advisor.setAdvice(new AfterReturningAdviceInterceptor());
    return advisor;
  }
}

class AfterReturningAdviceInterceptor implements AfterReturningAdvice {
  private final Logger logger = LoggerFactory.getLogger(AfterReturningAdviceInterceptor.class);

  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target) {
    logger.debug("-------------------> after interceptor : {}", returnValue);
  }
}

class Employ {
  private String name;

  @TargetAOP
  public String nameUpdate(String name) {
    this.name = name;
    return name;
  }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TargetAOP {
}
