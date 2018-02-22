package org.fxb.web.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 22.
 */
public class ModuleContextService {
  private final Logger logger = LoggerFactory.getLogger(ModuleContextService.class);

  private final ModuleContextManager moduleContextManager;
  private final ModuleDetailsService moduleContextService;

  public ModuleContextService(ModuleContextManager moduleContextManager,
      ModuleDetailsService moduleContextService) {
    this.moduleContextManager = moduleContextManager;
    this.moduleContextService = moduleContextService;
  }

  public synchronized void sync(String moduleId) {
    if (moduleContextManager.isExists(moduleId)) {
      logger.debug("{} : {}", moduleId, "the module is exists");
    }
    moduleContextManager.addModule(this.moduleContextService.getModule(moduleId));
  }
}
