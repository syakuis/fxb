package org.fxb.module.init;

import org.fxb.module.ModuleContextManager2;
import org.fxb.module.TestConfiguration;
import org.fxb.module.model.Module;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = { "moduleContext.basePackages = org.fxb.module"})
@ContextConfiguration(classes = { TestConfiguration.class })
public class ModuleContextManagerInitTest {
  @Autowired
  private ModuleContextManager2 moduleContextManager;

  /**
   * {@link ModuleInfoTest} load test
   */
  @Test
  public void moduleInfo() {
    ModuleInfoTest moduleInfo = new ModuleInfoTest();
    Module module = moduleInfo.getObject();
    Module _module = moduleContextManager.getModule(module.getModuleId());

    Assert.assertEquals(module.getModuleId(), _module.getModuleId());
  }

  /**
   * {@link ModuleInfoTest2} load test
   */
  @Test
  public void moduleInfo2() {
    ModuleInfoTest2 moduleInfo = new ModuleInfoTest2();
    Module module = moduleInfo.getObject();
    Module _module = moduleContextManager.getModule(module.getModuleId());

    Assert.assertEquals(module.getModuleId(), _module.getModuleId());
  }

}
