package org.fxb.module;

import java.util.List;
import org.fxb.module.domain.ModuleEntity;
import org.fxb.module.service.ModuleService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 22.
 */
public class ModuleContextServiceTest extends TestConfiguration {

  @Autowired
  ModuleContextManager2 moduleContextManager;

  @Autowired
  ModuleContextService moduleContextService;

  @Autowired
  ModuleService moduleService;

  @Test
  public void test() {
    List<ModuleEntity> modules = moduleService.getModules();

    for (ModuleEntity module : modules) {
      moduleContextService.sync(module.getModuleId());

      Assert.assertTrue(moduleContextManager.isExists(module.getModuleId()));
    }
  }

}
