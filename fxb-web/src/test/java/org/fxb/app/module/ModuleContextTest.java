package org.fxb.app.module;

import org.fxb.app.module.domain.ModuleEntity;
import org.fxb.app.module.mybatis.config.DataInitialization;
import org.fxb.app.module.mybatis.config.ModuleConfiguration;
import org.fxb.app.module.mybatis.config.ModuleOptionConfiguration;
import org.fxb.app.module.service.ModuleService;
import org.fxb.boot.Bootstrapping;
import org.fxb.web.module.ModuleContext;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

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
public class ModuleContextTest {
  @Resource(name = "myBatisModuleService")
  private ModuleService moduleService;

  @Autowired
  private ModuleContext moduleContext;

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

    Assert.assertThat("초기화 데이터 검증", moduleService.getModules().size(), is(dataRow));
  }

  @Test
  public void test() {
    System.out.println(moduleContext.get());
  }
}