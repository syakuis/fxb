package org.fxb.app.module.service;

import org.fxb.app.module.config.ModuleConfiguration;
import org.fxb.app.module.domain.ModuleEntity;
import org.fxb.app.module.mapper.config.DataInitialization;
import org.fxb.app.module.mapper.config.ModuleInitializationConfiguration;
import org.fxb.boot.Bootstrapping;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
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
@Import(value = {ModuleConfiguration.class, ModuleInitializationConfiguration.class})
@ActiveProfiles({ "test", "mybatis" })
public class MyBatisModuleServiceTest {
  @Resource(name = "moduleService")
  private ModuleService moduleService;

  String moduleId = "test";
  String moduleName = "test";
  String moduleIdx = null;
  int dataRow = 10;

  @Before
  public void init() {
    Assert.assertNotNull(moduleService);
    Assert.assertThat(moduleService.getModules(moduleName), IsEmptyCollection.empty());

    for (int i = 0; i < dataRow; i++) {
      ModuleEntity moduleEntity = DataInitialization.module(moduleName, i == 0 ? moduleId : moduleId + i, false);
      moduleEntity.setModuleOptions(DataInitialization.moduleOptions(null, dataRow));
      moduleService.saveModule(moduleEntity);
      if (i == 0) moduleIdx = moduleEntity.getModuleIdx();
    }
  }

  @Test
  @Transactional
  public void initDataChecking() {
    List<ModuleEntity> modules = moduleService.getModules();
    Assert.assertThat(modules.size(), is(dataRow));

    ModuleEntity moduleCheck = moduleService.getModule(moduleIdx);
    ModuleEntity moduleCheck2 = moduleService.getModule(moduleCheck.getModuleIdx());

    Assert.assertThat(moduleCheck, is(moduleCheck2));
    Assert.assertThat(moduleCheck.getModuleOptions(), is(moduleCheck2.getModuleOptions()));
    Assert.assertThat(moduleCheck.getModuleOptions().size(), is(dataRow));
  }

  @Test
  @Transactional
  public void update() {

    for (int i = 0; i < dataRow; i++) {
      if (i % 2 == 0) {
        ModuleEntity module = DataInitialization.module(moduleName, i == 0 ? moduleId : moduleId + i, false);
        module.setModuleOptions(DataInitialization.moduleOptions(null, dataRow));
        moduleService.saveModule(module);
      }
    }
  }

  public void delete() {
    moduleService.deleteModule(moduleIdx);
    Assert.assertNull(moduleService.getModule(moduleIdx));
  }
}
