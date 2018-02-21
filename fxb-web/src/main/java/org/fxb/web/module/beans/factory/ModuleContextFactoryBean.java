package org.fxb.web.module.beans.factory;

import java.util.ArrayList;
import java.util.List;
import org.fxb.web.module.ModuleInitialization;
import org.fxb.web.module.ModuleContextManager;
import org.fxb.web.module.annotation.ModuleInit;
import org.fxb.web.module.model.ModuleDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

/**
 * basePackages 경로를 스캔하여 {@link ModuleInit} 를 찾아 {@link ModuleContextManager} 추가한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 19.
 */
public class ModuleContextFactoryBean extends AbstractFactoryBean<ModuleContextManager> {
  private final Logger logger = LoggerFactory.getLogger(ModuleContextFactoryBean.class);
  private ClassPathScanningCandidateComponentProvider provider;

  private ModuleContextManager moduleContextManager;
  private String[] basePackages;

  public void setBasePackages(String basePackages) {
    this.basePackages = StringUtils.commaDelimitedListToStringArray(basePackages);
  }

  @Override
  public Class<ModuleContextManager> getObjectType() {
    return ModuleContextManager.class;
  }

  @Override
  protected ModuleContextManager createInstance() {
    moduleContextManager = new ModuleContextManager();
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
