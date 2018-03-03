package org.fxb.module.init;

import org.fxb.module.ModuleInitialization;
import org.fxb.module.data.ModuleInit;
import org.fxb.module.model.Module;
import org.fxb.module.model.ModuleDetails;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 11.
 */
@ModuleInit
public class ModuleInfoTest implements ModuleInitialization {
  @Override
  public Module getObject() {
    return new ModuleDetails("module", "module");
  }
}
