package org.fxb.web.module;

import java.util.List;
import org.fxb.web.module.model.Module;
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

  private final ModuleContextManager moduleContextManager;
  private final ModuleDetailsService moduleContextService;

  public ModuleContextService(ModuleContextManager moduleContextManager,
      ModuleDetailsService moduleContextService) {
    Assert.notNull(moduleContextManager, "The class must not be null");
    Assert.notNull(moduleContextService, "The class must not be null");
    this.moduleContextManager = moduleContextManager;
    this.moduleContextService = moduleContextService;
  }

  public void init() {
    this.sync();
  }

  public void sync() {
    moduleContextManager.destory();
    List<Module> modules = moduleContextService.getModules();
    for (Module module : modules) {
      moduleContextManager.addModule(module);
    }
  }

  public void sync(String moduleId) {
    if (moduleContextManager.isExists(moduleId)) {
      logger.warn("{} : {}", moduleId, "the module is exists");
    }
    moduleContextManager.addModule(this.moduleContextService.getModule(moduleId));
  }
}
