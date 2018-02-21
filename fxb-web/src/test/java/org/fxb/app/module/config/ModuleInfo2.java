package org.fxb.app.module.config;

import org.fxb.web.module.ModuleInitialization;
import org.fxb.web.module.model.Module;
import org.fxb.web.module.model.ModuleDetails;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 11.
 */
@org.fxb.web.module.annotation.ModuleInit
public class ModuleInfo2 implements ModuleInitialization {
  @Override
  public Module getObject() {
    return new ModuleDetails("module2", "module2");
  }
}
