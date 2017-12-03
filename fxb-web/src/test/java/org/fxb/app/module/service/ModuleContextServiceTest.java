package org.fxb.app.module.service;

import org.fxb.app.module.domain.ModuleEntity;
import org.fxb.app.module.mybatis.config.DataInitialization;
import org.fxb.app.module.mybatis.config.ModuleConfiguration;
import org.fxb.app.module.mybatis.config.ModuleOptionConfiguration;
import org.fxb.boot.Bootstrapping;
import org.fxb.web.module.ModuleContextService;
import org.fxb.web.module.model.Module;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
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
 * @since 2017. 11. 30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Bootstrapping.class)
@Import({ModuleConfiguration.class, ModuleOptionConfiguration.class})
@ActiveProfiles({"test", "mybatis"})
public class ModuleContextServiceTest {
  @Resource(name = "myBatisModuleService")
  private ModuleService moduleService;

  @Resource(name = "myBatisModuleContextService")
  private ModuleContextService moduleContextService;

  String moduleId = "test";
  String moduleName = "test";
  int dataRow = 10;

  @Test
  @Transactional
  public void test() {
//    Assert.assertNotNull(moduleService);
//    Assert.assertThat(moduleService.getModules(moduleName), IsEmptyCollection.empty());
//
//    for (int i = 0; i < dataRow; i++) {
//      moduleService.saveModule(
//        DataInitialization.module(moduleName, i == 0 ? moduleId : moduleId + i, false),
//        DataInitialization.moduleOptions(null, dataRow)
//      );
//    }
//
//    Assert.assertThat("초기화 데이터 검증", moduleService.getModules().size(), is(dataRow));
//
//    List<Module> modules = moduleContextService.getModules();
//    List<Module> modules2 = moduleContextService.getModules();
//    List<Module> modules3 = moduleContextService.getModules();
  }
}