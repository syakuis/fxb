package org.fxb.web.module;

import org.fxb.app.module.ModuleTestConfiguration;
import org.fxb.app.module.config.ModuleInfo;
import org.fxb.app.module.config.ModuleInfo2;
import org.fxb.web.module.model.Module;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 21.
 */
public class ModuleContextInitTest extends ModuleTestConfiguration {
  @Autowired
  private ModuleContextManager moduleContextManager;

  /**
   * {@link org.fxb.app.module.config.ModuleInfo} load test
   */
  @Test
  public void moduleInfo() {
    ModuleInfo moduleInfo = new ModuleInfo();
    Module module = moduleInfo.getObject();
    Module _module = moduleContextManager.getModule(module.getModuleId());

    Assert.assertEquals(module.getModuleId(), _module.getModuleId());
  }

  /**
   * {@link ModuleInfo2} load test
   */
  @Test
  public void moduleInfo2() {
    ModuleInfo2 moduleInfo = new ModuleInfo2();
    Module module = moduleInfo.getObject();
    Module _module = moduleContextManager.getModule(module.getModuleId());

    Assert.assertEquals(module.getModuleId(), _module.getModuleId());
  }

}
