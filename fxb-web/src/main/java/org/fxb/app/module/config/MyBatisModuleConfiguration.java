package org.fxb.app.module.config;

import org.fxb.app.module.dao.ModuleDAO;
import org.fxb.app.module.dao.ModuleOptionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
@Profile("mybatis")
@Configuration
public class MyBatisModuleConfiguration extends ModuleConfiguration {
  public MyBatisModuleConfiguration(
    @Qualifier("moduleDAO")
    ModuleDAO moduleDAO,
    @Qualifier("moduleOptionDAO")
    ModuleOptionDAO moduleOptionDAO) {
    super(moduleDAO, moduleOptionDAO);
  }
}
