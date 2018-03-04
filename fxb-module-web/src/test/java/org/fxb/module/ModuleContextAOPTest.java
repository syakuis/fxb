package org.fxb.module;

import java.util.Date;
import org.fxb.module.domain.ModuleEntity;
import org.fxb.module.service.ModuleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 23.
 */
@Transactional
public class ModuleContextAOPTest {
  @Autowired
  private ModuleContextManager moduleContextManager;
  @Autowired
  private ModuleContextService moduleContextService;
  @Autowired
  private ModuleService moduleService;

  @Before
  public void before() {
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
