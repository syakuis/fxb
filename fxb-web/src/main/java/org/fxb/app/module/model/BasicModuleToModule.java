package org.fxb.app.module.model;

import org.fxb.web.module.model.Module;
import org.fxb.web.module.model.ModuleBinding;
import org.fxb.web.module.model.ModuleDetails;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 23.
 */
public class BasicModuleToModule implements ModuleBinding {
  private final BasicModule basicModule;

  public BasicModuleToModule(BasicModule basicModule) {
    this.basicModule = basicModule;
  }

  @Override
  public Module getModule() {
    Module module = new ModuleDetails(basicModule.getModuleIdx(), basicModule.getModuleId());
    module.setSid(basicModule.getModuleId());
    module.setName(basicModule.getBrowserTitle());
    module.setOptions(ModuleDetails.setOptions(basicModule.getOptions()));
    return module;
  }
}
