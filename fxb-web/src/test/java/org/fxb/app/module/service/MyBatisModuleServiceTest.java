package org.fxb.app.module.service;

import org.fxb.app.module.domain.Module;
import org.fxb.boot.Bootstrapping;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Bootstrapping.class)
@ActiveProfiles({ "dev", "mybatis" })
public class MyBatisModuleServiceTest {
  @Resource(name = "myBatisModuleService")
  private MyBatisModuleService moduleService;

  @Test
  public void good() {
    Assert.assertNotNull(moduleService);

    Module module = moduleService.getModule(null, "test");

    if (module == null) {
      module = new Module();
      module.setModuleName("test");
      module.setModuleId("test");
    } else {
      module.setBrowserTitle("good");
    }

    moduleService.saveModule(module);

    Assert.assertNotNull(module.getModuleIdx());

    List<Module> modules = moduleService.getModules(module.getModuleName());

    Assert.assertEquals(modules.size(), 1);

    Assert.assertNotNull(module);

    moduleService.deleteModule(module.getModuleIdx());

    module = moduleService.getModule(module.getModuleIdx(), null);

    Assert.assertNull(module);
  }

  // todo service method 들 분리하기

  // todo transaction test
}
