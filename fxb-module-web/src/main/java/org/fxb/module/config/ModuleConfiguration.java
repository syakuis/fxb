package org.fxb.module.config;

import javax.annotation.Resource;
import org.aspectj.lang.annotation.Aspect;
import org.fxb.context.mybatis.annotation.Mapper;
import org.fxb.module.ModuleContextManager;
import org.fxb.module.ModuleContextService;
import org.fxb.module.ModuleDetailsService;
import org.fxb.module.config.bean.factory.ModuleContextFactoryBean;
import org.fxb.module.dao.ModuleDAO;
import org.fxb.module.service.ModuleDetailsServiceImpl;
import org.fxb.module.service.ModuleService;
import org.fxb.module.service.ModuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.util.Assert;

/**
 * mapper 와 jpa 를 모두 사용하기 위한 DI 설정 클래스
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 *
 * @see org.fxb.module.aop.ModuleContextAOP
 * @see org.fxb.module.dao.ModuleDAO
 * @see org.fxb.module.dao.ModuleOptionDAO
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(
    basePackages = "org.fxb.module",
    useDefaultFilters = false,
    includeFilters = {
        @Filter(type = FilterType.ANNOTATION, classes = { Aspect.class, Mapper.class }),
    }
)
public class ModuleConfiguration {
  @Resource(name = "moduleDAO")
  private ModuleDAO moduleDAO;

  @Bean
  public ModuleContextManager moduleContextManager() throws Exception {
    ModuleContextFactoryBean moduleContextFactoryBean = new ModuleContextFactoryBean();
    // todo config
    moduleContextFactoryBean.setBasePackages("org.fxb");
    moduleContextFactoryBean.afterPropertiesSet();
    return moduleContextFactoryBean.getObject();
  }

  @Autowired
  private ModuleContextManager moduleContextManager;

  @Bean
  @DependsOn({"moduleDAO"})
  public ModuleService moduleService() {
    Assert.notNull(moduleDAO, "it must not be null");
    ModuleServiceImpl moduleService = new ModuleServiceImpl();
    moduleService.setModuleDAO(moduleDAO);
    return moduleService;
  }

  @Autowired
  private ModuleService moduleService;

  @Bean(value = "moduleContextService")
  @DependsOn({"moduleService"})
  public ModuleContextService moduleContextService() {
    Assert.notNull(moduleContextManager, "it must not be null");
    Assert.notNull(moduleService, "it must not be null");
    ModuleDetailsService moduleDetailsService = new ModuleDetailsServiceImpl(moduleService);

    return new ModuleContextService(moduleContextManager, moduleDetailsService);
  }
}
