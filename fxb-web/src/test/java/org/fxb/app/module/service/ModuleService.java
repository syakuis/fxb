package org.fxb.app.module.service;

import org.fxb.web.module.model.ModuleDetails;
import org.fxb.web.module.model.OptionDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
public class ModuleService {

  public List<ModuleDetails> getModules() {
    return new ArrayList<>();
  }

  public ModuleDetails getModule(String moduleIdx) {
    return null;
  }

  public List<OptionDetails> getModuleOptions() {
    return new ArrayList<>();
  }

  public List<OptionDetails> getModuleOptions(String moduleIdx) {
    return new ArrayList<>();
  }
}
