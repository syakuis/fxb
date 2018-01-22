package org.fxb.web.module.beans.factory;

import org.fxb.web.module.CreateModule;
import org.fxb.web.module.ModuleContext;
import org.fxb.web.module.ModuleContextManager;
import org.fxb.web.module.annotation.Created;
import org.fxb.web.module.model.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
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
    provider.addIncludeFilter(new AnnotationTypeFilter(Created.class));

    for (String basePackage : basePackages) {
      List<CreateModule> modules = findAnnotatedClasses(basePackage);
      for (CreateModule module : modules) {
        moduleContextManager.addModule(module.name(), module.value());
        logger.debug("{} module create.", module.name());
      }
    }

    return new ModuleContextManager();
  }

  private List<CreateModule> findAnnotatedClasses(String scanPackage) {
    List<CreateModule> modules = new ArrayList<>();
    for (BeanDefinition bean : provider.findCandidateComponents(scanPackage)) {
      try {
        Class<?> clazz = Class.forName(bean.getBeanClassName());
        if (clazz == CreateModule.class) {
          modules.add((CreateModule) clazz.newInstance());
        } else {
          logger.debug("class not module.class {}", bean.getBeanClassName());
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
