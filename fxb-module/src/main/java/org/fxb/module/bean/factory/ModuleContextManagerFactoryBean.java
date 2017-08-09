package org.fxb.module.bean.factory;

import java.lang.annotation.Annotation;
import java.util.function.Function;
import org.fxb.module.Module;
import org.fxb.module.ModuleDefinition;
import org.fxb.module.ModuleContextManager;
import org.fxb.module.ModuleDefinitionScanner;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.util.StringUtils;

/**
 * {@link ModuleDefinition} 주석을 검색하여 {@link Module} 로 정의하고
 * {@link ModuleContextManager#context} 추가한다.
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 3. 28.
 * @see Module
 * @see ModuleContextManager
 * @see ModuleDefinitionScanner
 * @see ModuleDefinition
 */
public class ModuleContextManagerFactoryBean extends AbstractFactoryBean<ModuleContextManager> {
  private String[] basePackages;
  private Class<? extends Annotation> annotationTypeFilter = ModuleDefinition.class;

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

    ModuleDefinitionScanner scanner = new ModuleDefinitionScanner(basePackages, annotationTypeFilter);
    return scanner.getModules().stream()
        .collect(
            ModuleContextManager::new,
            ModuleContextManager::add,
            (moduleContextManager1, moduleContextManager2) -> Function.identity());
  }
}