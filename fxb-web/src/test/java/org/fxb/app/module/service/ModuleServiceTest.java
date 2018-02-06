package org.fxb.app.module.service;

import org.fxb.app.module.ModuleTestConfiguration;
import org.fxb.app.module.domain.Module;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Transactional
public class ModuleServiceTest extends ModuleTestConfiguration {
  @Resource(name = "moduleService")
  private ModuleService moduleService;

  @Autowired
  @Qualifier("fxbDataSource")
  DataSource dataSource;

  @Before
  public void before() {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScript(new ClassPathResource("org/fxb/app/module/schemas/module.data.h2.sql"));
    DatabasePopulatorUtils.execute(populator, dataSource);
  }

  @Test
  public void selectModule() {
    Assert.assertEquals(moduleService.getModules().size(), 4);
  }

  @Test
  public void insertModule() {
    Module module = new Module();
    module.setModuleId("test5");
    module.setModuleName("test5");
    module.setRegDate(new Date());
    moduleService.saveModule(module);

    Assert.assertEquals(moduleService.getModules().size(), 5);
  }

  @Test(expected = DuplicateKeyException.class)
  public void insertModuleException() {
    Module module = new Module();
    module.setModuleId("test");
    module.setModuleName("test5");
    module.setRegDate(new Date());
    moduleService.saveModule(module);

    Assert.assertEquals(moduleService.getModules().size(), 4);
  }

  @Test
  public void delete() {
    moduleService.deleteModule("test");
    Assert.assertEquals(moduleService.getModules().size(), 3);
  }
}
