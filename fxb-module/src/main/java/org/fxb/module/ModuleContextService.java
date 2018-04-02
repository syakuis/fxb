package org.fxb.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 22.
 */
public class ModuleContextService {
  private final Logger logger = LoggerFactory.getLogger(ModuleContextService.class);

  private final ModuleContextManager2 moduleContextManager;
  private final ModuleDetailsService moduleContextService;

  public ModuleContextService(ModuleContextManager2 moduleContextManager,
      ModuleDetailsService moduleContextService) {
    Assert.notNull(moduleContextManager, "The moduleContextManager must not be null");
    Assert.notNull(moduleContextService, "The moduleContextService must not be null");
    this.moduleContextManager = moduleContextManager;
    this.moduleContextService = moduleContextService;
  }

  public void init() {
    this.sync();
  }

  public void sync() {
    moduleContextManager.destory();
    moduleContextService.getModules()
        .stream().forEach(module -> moduleContextManager.addModule(module));
  }

  public void sync(String moduleId) {
    if (moduleContextManager.isExists(moduleId)) {
      logger.warn("{} : {}", moduleId, "the module is exists");
    }
    moduleContextManager.addModule(this.moduleContextService.getModule(moduleId));
  }
}
