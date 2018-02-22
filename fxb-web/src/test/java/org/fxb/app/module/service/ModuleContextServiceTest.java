package org.fxb.app.module.service;

import java.util.List;
import org.fxb.app.module.ModuleTestConfiguration;
import org.fxb.app.module.domain.Module;
import org.fxb.web.module.ModuleContextManager;
import org.fxb.web.module.ModuleContextService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 22.
 */
@Transactional
public class ModuleContextServiceTest extends ModuleTestConfiguration {

  @Autowired
  ModuleContextManager moduleContextManager;

  @Autowired
  ModuleContextService moduleContextService;

  @Autowired
  ModuleService moduleService;

  @Test
  public void test() {
    List<Module> modules = moduleService.getModules();

    for (Module module : modules) {
      moduleContextService.sync(module.getModuleId());

      Assert.assertTrue(moduleContextManager.isExists(module.getModuleId()));
    }
  }

}
