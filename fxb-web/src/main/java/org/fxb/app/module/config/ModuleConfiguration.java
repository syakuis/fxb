package org.fxb.app.module.config;

import org.fxb.app.module.dao.ModuleDAO;
import org.fxb.app.module.dao.ModuleOptionDAO;
import org.fxb.app.module.service.ModuleContextServiceImpl;
import org.fxb.app.module.service.ModuleService;
import org.fxb.app.module.service.ModuleServiceImpl;
import org.fxb.web.module.ModuleContextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.Assert;

/**
 * mapper 와 jpa 를 모두 사용하기 위한 DI 설정 클래스
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
public class ModuleConfiguration {
  private final Logger logger = LoggerFactory.getLogger(ModuleConfiguration.class);

  private final ModuleDAO moduleDAO;
  private final ModuleOptionDAO moduleOptionDAO;

  private ModuleService moduleService;
  private ModuleContextService moduleContextService;

  public ModuleConfiguration(ModuleDAO moduleDAO, ModuleOptionDAO moduleOptionDAO) {
    Assert.notNull(moduleDAO, "this argument is required; it must not be null");
    Assert.notNull(moduleOptionDAO, "this argument is required; it must not be null");
    this.moduleDAO = moduleDAO;
    this.moduleOptionDAO = moduleOptionDAO;
  }

  @Bean
  @DependsOn({"moduleDAO"})
  public ModuleService moduleService() {
    Assert.notNull(moduleDAO, "this argument is required; it must not be null");
    ModuleServiceImpl moduleService = new ModuleServiceImpl();
    moduleService.setModuleDAO(moduleDAO);
    this.moduleService = moduleService;
    return moduleService;
  }

  @Bean
  @DependsOn({"moduleService"})
  public ModuleContextService moduleContextService() {
    Assert.notNull(moduleService, "this argument is required; it must not be null");
    ModuleContextServiceImpl moduleContextService = new ModuleContextServiceImpl();
    moduleContextService.setModuleService(moduleService);
    this.moduleContextService = moduleContextService;
    return moduleContextService;
  }

//  @Bean
//  @DependsOn("moduleContextService")
//  public ModuleContext moduleContext() {
//    Assert.notNull(moduleContextService, "this argument is required; it must not be null");
//    ModuleContext moduleContext = new ModuleContext(moduleContextService);
//    return moduleContext;
//  }
}
