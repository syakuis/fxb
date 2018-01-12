package org.fxb.web.module;

import org.fxb.web.module.model.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 8.
 */
public abstract class BasicModuleContextService implements ModuleContextService {
  @Override
  public abstract Map<String, Module> getModuleContext();

  @Override
  public List<String> getModuleIdx() {
    List<String> moduleIdxIndex = new ArrayList<>();

    for(Map.Entry<String, Module> entry : this.getModuleContext().entrySet()) {
      Module moduleDetails = entry.getValue();
      moduleIdxIndex.add(moduleDetails.getModuleIdx());
    }

    return moduleIdxIndex;
  }

  @Override
  public List<String> getId() {
    List<String> idIndex = new ArrayList<>();

    for(Map.Entry<String, Module> entry : this.getModuleContext().entrySet()) {
      Module moduleDetails = entry.getValue();
      idIndex.add(createId(moduleDetails.getMid(), moduleDetails.getSid()));
    }

    return idIndex;
  }

  @Override
  public String createId(String mid, String sid) {
    return new StringBuffer(mid).append("+").append(sid).toString();
  }
}
