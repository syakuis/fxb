package org.fxb.boot.config;

import org.fxb.web.module.annotation.Created;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 16.
 */
public class AnnotationClassScannerTest {

  @Test
  public void test() {
    System.out.println("Finding annotated classes using Spring:");
    new AnnotationClassScannerTest().findAnnotatedClasses("org.fxb.app");
  }

  public void findAnnotatedClasses(String scanPackage) {
    ClassPathScanningCandidateComponentProvider provider = createComponentScanner();
    for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) {
      printMetadata(beanDef);
    }
  }

  private ClassPathScanningCandidateComponentProvider createComponentScanner() {
    // Don't pull default filters (@Component, etc.):
    ClassPathScanningCandidateComponentProvider provider
      = new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(new AnnotationTypeFilter(Created.class));
    return provider;
  }

  private void printMetadata(BeanDefinition beanDef) {
    try {
      Class<?> cl = Class.forName(beanDef.getBeanClassName());
      Created findable = cl.getAnnotation(Created.class);
      System.out.printf("Found class: %s, with meta name: %s%n",
        cl.getSimpleName(), findable);
    } catch (Exception e) {
      System.err.println("Got exception: " + e.getMessage());
    }
  }
}
