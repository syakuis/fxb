package org.fxb.module.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.fxb.module.ModuleContextManager;
import org.fxb.module.TestConfiguration;
import org.fxb.module.model.Module;
import org.fxb.module.model.ModuleDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = { "moduleContext.basePages = org.fxb.module"})
@ContextConfiguration(classes = TestConfiguration.class)
public class ModuleContextManagerTest {
  @Autowired
  private ModuleContextManager moduleContextManager;

  int data = 1000;
  Map<String, Module> moduleMap = new HashMap<>();
  String lastModuleId = null;

  @Before
  public void prepared() {
    for (int i = 0; i < data; i++) {
      String moduleId = UUID.randomUUID().toString();
      Module module = new ModuleDetails("module", moduleId);
      moduleContextManager.addModule(module);
      moduleMap.put(moduleId, module);
      if (i == data - 1)lastModuleId = moduleId;
    }
  }

  @Test
  public void test() {
    // Map to List
    List<Module> modules = moduleContextManager.getModules();
    Assert.assertTrue(modules.size() == data);

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

    Assert.assertTrue(moduleContextManager.getModules().equals(new ArrayList<>(moduleMap.values())));

    Assert.assertNotNull(lastModuleId);
    Assert.assertTrue(moduleContextManager.isExists(lastModuleId));

    moduleContextManager.destory();
    Assert.assertTrue(moduleContextManager.getModule().isEmpty());
  }

}
