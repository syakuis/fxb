package org.fxb.module.config.bean.factory;


import java.util.ArrayList;
import java.util.List;
import org.fxb.module.ModuleContextManager2;
import org.fxb.module.ModuleInitialization;
import org.fxb.module.data.ModuleInit;
import org.fxb.module.model.ModuleDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

/**
 * basePackages 경로를 스캔하여 {@link ModuleInit} 를 찾아 {@link ModuleContextManager2} 추가한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 19.
 */
public class ModuleContextFactoryBean extends AbstractFactoryBean<ModuleContextManager2> {
  private final Logger logger = LoggerFactory.getLogger(ModuleContextFactoryBean.class);
  private ClassPathScanningCandidateComponentProvider provider;

  private ModuleContextManager2 moduleContextManager;
  private String[] basePackages;

  public void setBasePackages(String basePackages) {
    this.basePackages = StringUtils.commaDelimitedListToStringArray(basePackages);
  }

  @Override
  public Class<ModuleContextManager2> getObjectType() {
    return ModuleContextManager2.class;
  }

  @Override
  protected ModuleContextManager2 createInstance() {
    moduleContextManager = new ModuleContextManager2();
    if (this.basePackages == null) return moduleContextManager;
    provider = new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(new AnnotationTypeFilter(ModuleInit.class));

    for (String basePackage : basePackages) {
      List<ModuleInitialization> modules = findAnnotatedClasses(basePackage);
      for (ModuleInitialization module : modules) {
        ModuleDetails moduleDetails = (ModuleDetails) module.getObject();
        if (moduleDetails != null && moduleDetails.getModuleId() != null) {
          moduleContextManager.addModule(moduleDetails);
          logger.debug("added {} ({}) in the ModuleContext.", moduleDetails.getModuleId(), moduleDetails.getModuleIdx());
        }
      }
    }

    return moduleContextManager;
  }

  /**
   * Module 정보를 수집한다.
   * @param basePackage
   * @return
   */
  private List<ModuleInitialization> findAnnotatedClasses(String basePackage) {
    List<ModuleInitialization> modules = new ArrayList<>();
    for (BeanDefinition bean : provider.findCandidateComponents(basePackage)) {
      try {
        Class<?> clazz = Class.forName(bean.getBeanClassName());
        if (clazz != null && clazz.getClass() == ModuleInitialization.class.getClass()) {
          modules.add((ModuleInitialization) clazz.newInstance());
          logger.debug("{} ModuleInitialization Instance.", clazz.getCanonicalName());
        }
      } catch (ClassNotFoundException e) {
        logger.error(e.getMessage(), e);
      } catch (InstantiationException e) {
        logger.error(e.getMessage(), e);
      } catch (IllegalAccessException e) {
        logger.error(e.getMessage(), e);
      }
    }

    return modules;
  }
}
