package org.fxb.web.module;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.fxb.app.module.ModuleTestConfiguration;
import org.fxb.web.module.model.Module;
import org.fxb.web.module.model.ModuleDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 22.
 */
public class ModuleContextTest extends ModuleTestConfiguration {
  @Autowired
  private ModuleContextManager moduleContextManager;

  int data = 1000;

  @Before
  public void prepared() {
    for (int i = 0; i < data; i++) {
      moduleContextManager.addModule(new ModuleDetails("module", UUID.randomUUID().toString()));
    }
  }

  @Test
  public void test() {
    // Map to List
    List<Module> modules = moduleContextManager.getModules();
    Assert.assertTrue(modules.size() > data);

    // Map copy
    Map<String, Module> module = moduleContextManager.getModule();

    Assert.assertEquals(moduleContextManager, module);
    Assert.assertEquals(moduleContextManager.hashCode(), module.hashCode());

    // Map modified
    module.put("module", null);
    Assert.assertNotEquals(moduleContextManager.hashCode(), module.hashCode());

    // moduleContextManager safe
    module.clear();
    Assert.assertTrue(module.isEmpty());
    Assert.assertFalse(moduleContextManager.getModule().isEmpty());
  }

}
