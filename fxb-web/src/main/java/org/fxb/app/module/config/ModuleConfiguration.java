package org.fxb.app.module.config;

import org.fxb.app.module.dao.ModuleDAO;
import org.fxb.app.module.dao.ModuleOptionDAO;
import org.fxb.app.module.service.ModuleDetailsServiceImpl;
import org.fxb.app.module.service.ModuleService;
import org.fxb.app.module.service.ModuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
//  @Autowired
//  private ModuleContextManager moduleContextManager;
//  private final ModuleDAO moduleDAO;
//  private final ModuleOptionDAO moduleOptionDAO;
//
//  private ModuleService moduleService;
//
//  public ModuleConfiguration(ModuleDAO moduleDAO, ModuleOptionDAO moduleOptionDAO) {
//    Assert.notNull(moduleDAO, "this argument is required; it must not be null");
//    Assert.notNull(moduleOptionDAO, "this argument is required; it must not be null");
//    this.moduleDAO = moduleDAO;
//    this.moduleOptionDAO = moduleOptionDAO;
//  }
//
//  public void setModuleContextManager(ModuleContextManager moduleContextManager) {
//    this.moduleContextManager = moduleContextManager;
//  }
//
//  @Bean
//  @DependsOn({"moduleDAO"})
//  public ModuleService moduleService() {
//    Assert.notNull(moduleDAO, "it must not be null");
//    ModuleServiceImpl moduleService = new ModuleServiceImpl();
//    moduleService.setModuleDAO(moduleDAO);
//    this.moduleService = moduleService;
//    return moduleService;
//  }
//
//  @Bean(value = "moduleContextService")
//  @DependsOn({"moduleService"})
//  public ModuleContextService moduleContextService() {
//    Assert.notNull(moduleContextManager, "it must not be null");
//    Assert.notNull(moduleService, "it must not be null");
//    ModuleDetailsService moduleDetailsService = new ModuleDetailsServiceImpl(moduleService);
//
//    return new ModuleContextService(moduleContextManager, moduleDetailsService);
//  }
}
