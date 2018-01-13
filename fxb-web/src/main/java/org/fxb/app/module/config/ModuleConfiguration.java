package org.fxb.app.module.config;

import org.fxb.app.module.dao.ModuleDAO;
import org.fxb.app.module.dao.ModuleOptionBatchDAO;
import org.fxb.app.module.dao.ModuleOptionDAO;
import org.fxb.app.module.service.*;
import org.fxb.web.module.ModuleContext;
import org.fxb.web.module.ModuleContextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
public class ModuleConfiguration {
  private final Logger logger = LoggerFactory.getLogger(ModuleConfiguration.class);

  private final ModuleDAO moduleDAO;
  private final ModuleOptionDAO moduleOptionDAO;

  private ModuleOptionBatchDAO moduleOptionBatchDAO;

  private ModuleService moduleService;
  private ModuleOptionService moduleOptionService;
  private ModuleContextService moduleContextService;

  public ModuleConfiguration(ModuleDAO moduleDAO, ModuleOptionDAO moduleOptionDAO) {
    Assert.notNull(moduleDAO, "this argument is required; it must not be null");
    Assert.notNull(moduleOptionDAO, "this argument is required; it must not be null");
    this.moduleDAO = moduleDAO;
    this.moduleOptionDAO = moduleOptionDAO;
  }

  @Bean
  @DependsOn("moduleOptionDAO")
  public ModuleOptionBatchDAO moduleOptionBatchDAO() {
    Assert.notNull(moduleOptionDAO, "this argument is required; it must not be null");
    moduleOptionBatchDAO = new ModuleOptionBatchDAO();
    moduleOptionBatchDAO.setModuleOptionDAO(moduleOptionDAO);
    return moduleOptionBatchDAO;
  }

  @Bean
  @DependsOn({"moduleDAO", "moduleOptionBatchDAO"})
  public ModuleService moduleService() {
    Assert.notNull(moduleDAO, "this argument is required; it must not be null");
    Assert.notNull(moduleOptionBatchDAO, "this argument is required; it must not be null");
    ModuleServiceImpl moduleService = new ModuleServiceImpl();
    moduleService.setModuleDAO(moduleDAO);
    moduleService.setModuleOptionBatchDAO(moduleOptionBatchDAO);
    this.moduleService = moduleService;
    return moduleService;
  }

  @Bean
  @DependsOn("moduleOptionDAO")
  public ModuleOptionService moduleOptionService() {
    Assert.notNull(moduleOptionDAO, "this argument is required; it must not be null");
    ModuleOptionServiceImpl moduleOptionService = new ModuleOptionServiceImpl();
    moduleOptionService.setModuleOptionDAO(moduleOptionDAO);
    this.moduleOptionService = moduleOptionService;
    return moduleOptionService;
  }

  @Bean
  @DependsOn({"moduleService", "moduleOptionService"})
  public ModuleContextService moduleContextService() {
    Assert.notNull(moduleService, "this argument is required; it must not be null");
    Assert.notNull(moduleOptionService, "this argument is required; it must not be null");
    ModuleContextServiceImpl moduleContextService = new ModuleContextServiceImpl();
    moduleContextService.setModuleService(moduleService);
    moduleContextService.setModuleOptionService(moduleOptionService);
    this.moduleContextService = moduleContextService;
    return moduleContextService;
  }

  @Bean
  @DependsOn("moduleContextService")
  public ModuleContext moduleContext() {
    Assert.notNull(moduleContextService, "this argument is required; it must not be null");
    ModuleContext moduleContext = new ModuleContext(moduleContextService);
    // 캐시를 생성하기 위해 최소 한번 호출한다.
    moduleContext.get();
    return moduleContext;
  }
}
