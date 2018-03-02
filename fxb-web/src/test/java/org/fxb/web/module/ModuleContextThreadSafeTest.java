package org.fxb.web.module;

import java.util.List;
import java.util.UUID;
import org.fxb.app.module.ModuleTestConfiguration;
import org.fxb.web.module.model.Module;
import org.fxb.web.module.model.ModuleDetails;
import org.jmock.lib.concurrent.Blitzer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public class ModuleContextThreadSafeTest extends ModuleTestConfiguration {
  private static final Logger logger = LoggerFactory.getLogger(ModuleContextThreadSafeTest.class);

  private Blitzer blitzer;

  @Autowired
  private ModuleContextManager moduleContextManager;

  @Before
  public void prepared() {
    blitzer = new Blitzer(1000, 10);
    for (int i = 0; i < 10000; i++) {
      moduleContextManager.addModule(new ModuleDetails("module", UUID.randomUUID().toString()));
    }
  }

  @After
  public void closed() {
    blitzer.shutdown();
  }

  private synchronized void syncJob(String moduleId, String title) {
    List<Module> modules = moduleContextManager.getModules();

    Assert.assertTrue(modules.size() > 10000);

    // 여러 스레드가 바라보고 있는 참조 타입이다. 복제를 해야한다.
    ModuleDetails moduleDetails = (ModuleDetails) moduleContextManager.getModule("module");
    Assert.assertNotNull(moduleDetails);
    Assert.assertNotNull(moduleDetails.getModuleIdx());

    // module exists test : no exception
    moduleDetails.setTitle(title);
    moduleContextManager.addModule(moduleDetails);

    Module module = moduleContextManager.getModule(moduleDetails.getModuleId());

    if (!title.equals(module.getTitle())) {
      logger.debug("{} ==========> {} =========> {}", title, moduleDetails.getTitle(), module.getTitle());
    }

    Assert.assertEquals(title, module.getTitle());

    Module newModule = new ModuleDetails(moduleId, moduleId);
    Assert.assertNotNull(newModule.getModuleIdx());
    Assert.assertNotNull(newModule.getCreatedDate());
    moduleContextManager.addModule(newModule);
    Assert.assertEquals(newModule.getModuleId(), moduleId);
  }

  @Test
  @Ignore
  public void syncTest() throws Exception {
    blitzer.blitz(new Runnable() {
      @Override
      public void run() {
        String name = Thread.currentThread().getName();
        syncJob(name, name);
      }
    });
  }

  @Test(expected = IllegalArgumentException.class)
  @Ignore
  public void test() {
    List<Module> modules = moduleContextManager.getModules();

    Assert.assertTrue(modules.size() > 10000);

    Module module = moduleContextManager.getModule("module");
    Assert.assertNotNull(module);
    Assert.assertNotNull(module.getModuleIdx());

    // module exists test : no exception
    ModuleDetails moduleDetails = (ModuleDetails) module;
    moduleDetails.setTitle("good!!!");
    moduleContextManager.addModule(moduleDetails);

    Assert.assertEquals(moduleContextManager.getModule(moduleDetails.getModuleId()).getTitle(), "good!!!");

    // module exists test : exception
    Module newModule = new ModuleDetails("module", "module");
    Assert.assertNotNull(newModule.getModuleIdx());
    Assert.assertNotNull(newModule.getCreatedDate());
    moduleContextManager.addModule(newModule);
  }
}