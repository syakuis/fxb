package org.fxb.web.module;

import org.fxb.web.module.model.Module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 19.
 */
public class ModuleContextManager implements Serializable {
  private static final long serialVersionUID = -7851817699437417342L;
  private static final Map<String, Module> context = new HashMap<>();

  public synchronized void addModule(String name, Module module) {
    this.context.put(name, module);
  }

  public synchronized List<Module> getModules() {
    return new ArrayList<>(this.context.values());
  }

  public synchronized Module getModule(String name) {
    return this.context.get(name);
  }

  public synchronized List<String> getModuleIdx() {
    List<String> moduleIdxIndex = new ArrayList<>();

    for(Map.Entry<String, Module> entry : this.context.entrySet()) {
      Module moduleDetails = entry.getValue();
      moduleIdxIndex.add(moduleDetails.getModuleIdx());
    }

    return moduleIdxIndex;
  }

  public synchronized List<String> getId() {
    List<String> idIndex = new ArrayList<>();

    for(Map.Entry<String, Module> entry : this.context.entrySet()) {
      Module moduleDetails = entry.getValue();
//      idIndex.add(createId(moduleDetails.getMid(), moduleDetails.getSid()));
    }

    return idIndex;
  }

  public synchronized String createId(String mid, String sid) {
    return new StringBuffer(mid).append("+").append(sid).toString();
  }
}
