package org.fxb.module.bean.factory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.fxb.module.Module;
import org.fxb.module.ModuleContext;
import org.fxb.module.ModuleContextManager;
import org.fxb.module.config.bean.factory.ModuleContextFactoryBean;
import org.jooq.lambda.Unchecked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

/**
 * ModuleContext 를 찾아 ModuleContextManager 추가한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 3. 28.
 * @see Module
 * @see ModuleContextManager
 * @see ModuleContext
 */
public class ModuleContextManagerFactoryBean extends AbstractFactoryBean<ModuleContextManager> {
private final Logger logger = LoggerFactory.getLogger(ModuleContextFactoryBean.class);
  private ClassPathScanningCandidateComponentProvider provider;

  private String[] basePackages;
  private Class<? extends Annotation> annotationTypeFilter = ModuleContext.class;

  public void setBasePackages(String basePackages) {
    this.basePackages = StringUtils.commaDelimitedListToStringArray(basePackages);
  }

  public void setBasePackages(String... basePackages) {
    this.basePackages = basePackages;
  }

  public void setAnnotationTypeFilter(
      Class<? extends Annotation> annotationTypeFilter) {
    this.annotationTypeFilter = annotationTypeFilter;
  }

  @Override
  public Class<ModuleContextManager> getObjectType() {
    return ModuleContextManager.class;
  }

  @Override
  protected ModuleContextManager createInstance() {
    if (this.basePackages == null) {
      return new ModuleContextManager();
    }

    provider = new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(new AnnotationTypeFilter(annotationTypeFilter));

    return Arrays.stream(basePackages)
        .map(this::findAnnotatedClasses)
        .flatMap(Collection::stream)
        .filter(module -> module != null && module.getModuleId() != null)
        .collect(
            ModuleContextManager::new,
            ModuleContextManager::addModule,
            (moduleContextManager1, moduleContextManager2) -> Function.identity());

//    logger.debug("{}:{} --- ModuleContextManager added.", module.getModuleId(), module.getModuleName());
  }

  /**
   * Module 를 찾는 다.
   * @param basePackage 찾을 대상 경로
   * @return 찾은 Module 을 {@link List} 로 반환한다.
   */
  private List<Module> findAnnotatedClasses(String basePackage) {
    return provider.findCandidateComponents(basePackage).stream()
      .map(Unchecked.function(beanDefinition -> {
        Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());

        if (clazz != null && clazz.getClass() == Module.class.getClass()) {
          logger.debug("{} --- Module Instance.", clazz.getCanonicalName());
          return (Module) clazz.newInstance();
        }

        return null;
      }))
      .filter(module -> module != null)
      .collect(Collectors.toList());
  }
}