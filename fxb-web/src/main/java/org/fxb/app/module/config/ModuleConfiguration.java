package org.fxb.app.module.config;

import org.fxb.app.module.dao.ModuleDAO;
import org.fxb.app.module.dao.ModuleOptionBatchDAO;
import org.fxb.app.module.dao.ModuleOptionDAO;
import org.fxb.app.module.service.*;
import org.fxb.web.module.ModuleContext;
import org.fxb.web.module.ModuleContextService;
import org.springframework.context.annotation.Bean;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
public class ModuleConfiguration {
  private final ModuleDAO moduleDAO;
  private final ModuleOptionDAO moduleOptionDAO;

  private ModuleOptionBatchDAO moduleOptionBatchDAO;

  private ModuleService moduleService;
  private ModuleOptionService moduleOptionService;
  private ModuleContextService moduleContextService;

  public ModuleConfiguration(ModuleDAO moduleDAO, ModuleOptionDAO moduleOptionDAO) {
    this.moduleDAO = moduleDAO;
    this.moduleOptionDAO = moduleOptionDAO;
  }

  @Bean
  public ModuleOptionBatchDAO moduleOptionBatchDAO() {
    moduleOptionBatchDAO = new ModuleOptionBatchDAO();
    moduleOptionBatchDAO.setModuleOptionDAO(moduleOptionDAO);
    return moduleOptionBatchDAO;
  }

  @Bean
  public ModuleService moduleService() {
    ModuleServiceImpl moduleService = new ModuleServiceImpl();
    moduleService.setModuleDAO(moduleDAO);
    moduleService.setModuleOptionBatchDAO(moduleOptionBatchDAO);
    this.moduleService = moduleService;
    return moduleService;
  }

  @Bean
  public ModuleOptionService moduleOptionService() {
    ModuleOptionServiceImpl moduleOptionService = new ModuleOptionServiceImpl();
    moduleOptionService.setModuleOptionDAO(moduleOptionDAO);
    this.moduleOptionService = moduleOptionService;
    return moduleOptionService;
  }

  @Bean
  public ModuleContextService moduleContextService() {
    ModuleContextServiceImpl moduleContextService = new ModuleContextServiceImpl();
    moduleContextService.setModuleService(moduleService);
    moduleContextService.setModuleOptionService(moduleOptionService);
    this.moduleContextService = moduleContextService;
    return moduleContextService;
  }

  @Bean
  public ModuleContext moduleContext() {
    return new ModuleContext(moduleContextService);
  }
}
