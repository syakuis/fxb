package org.fxb.module;

import java.util.Date;
import org.fxb.module.domain.ModuleEntity;
import org.fxb.module.service.ModuleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 23.
 */
public class ModuleContextAOPTest extends TestConfiguration {
  @Autowired
  private ModuleContextManager moduleContextManager;
  @Autowired
  private ModuleContextService moduleContextService;
  @Autowired
  private ModuleService moduleService;

  @Before
  public void before() {
    Assert.assertNotNull(moduleContextManager);
    Assert.assertNotNull(moduleContextService);
    Assert.assertNotNull(moduleService);
    moduleContextService.init();
  }

  @Test
  public void save() {
    moduleService.saveModule(new ModuleEntity("moduleid", "moduleid", new Date()));
    Assert.assertTrue(moduleContextManager.isExists("moduleid"));
  }

  @Test
  public void delete() {
    Assert.assertTrue(moduleContextManager.isExists("test"));
    moduleService.deleteModule("test");
    Assert.assertFalse(moduleContextManager.isExists("test"));
  }

}
