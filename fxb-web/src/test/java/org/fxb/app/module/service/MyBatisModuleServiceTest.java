package org.fxb.app.module.service;

import org.fxb.app.module.domain.Module;
import org.fxb.app.module.domain.ModuleOptions;
import org.fxb.app.module.model.BasicModule;
import org.fxb.app.module.mybatis.config.DataInitialization;
import org.fxb.app.module.mybatis.config.ModuleConfiguration;
import org.fxb.app.module.mybatis.config.ModuleOptionsConfiguration;
import org.fxb.boot.Bootstrapping;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Bootstrapping.class)
@Import(value = {ModuleConfiguration.class, ModuleOptionsConfiguration.class})
@ActiveProfiles({ "test", "mybatis" })
public class MyBatisModuleServiceTest {
  @Resource(name = "myBatisModuleService")
  private MyBatisModuleService moduleService;

  String moduleId = "test";
  String moduleName = "test";
  int dataRow = 10;

  @Test
  @Transactional
  public void test() {
    Assert.assertNotNull(moduleService);
    Assert.assertThat(moduleService.getModules(moduleName), IsEmptyCollection.empty());

    Module module = moduleService.saveModule(
      DataInitialization.module(moduleName, moduleId, false),
      DataInitialization.moduleOptions(null, dataRow)
    );

    Module moduleCheck = moduleService.getModule(module.getModuleIdx(), null);
    Assert.assertThat(module, is(moduleCheck));
    Assert.assertThat(module.getModuleOptions(), is(moduleCheck.getModuleOptions()));
    Assert.assertThat(moduleCheck.getModuleOptions().size(), is(dataRow));

    moduleService.deleteModule(module.getModuleIdx());

    Assert.assertNull(moduleService.getModule(moduleCheck.getModuleIdx(), null));
  }
}
