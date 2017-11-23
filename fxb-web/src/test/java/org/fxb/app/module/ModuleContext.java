package org.fxb.app.module;

import lombok.ToString;
import org.apache.commons.beanutils.BeanUtils;
import org.fxb.app.module.model.Module;
import org.fxb.app.module.model.ModuleDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * DB module 데이터를 공유하기 위한 저장소.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
@ToString
public class ModuleContext {
  private static final Logger logger = LoggerFactory.getLogger(ModuleContext.class);

  private Map<String, Module> context = new HashMap<>();
  private List<String> moduleIdxIndex = new ArrayList<>();
  private List<String> idIndex = new ArrayList<>();

  private synchronized void reload() {
    this.moduleIdxIndex.clear();
    this.idIndex.clear();

    for(Map.Entry<String, Module> entry : this.context.entrySet()) {
      Module moduleDetails = entry.getValue();
      this.moduleIdxIndex.add(moduleDetails.getModuleIdx());
      this.idIndex.add(moduleDetails.getMid() + "+" + moduleDetails.getSid());
    }
  }

  public Map<String, Module> get() {
    return this.context;
  }

  public Module get(String moduleIdx) {
    return this.context.get(moduleIdx);
  }

  public String getMid(String moduleIdx) {
    Module module = this.context.get(moduleIdx);
    if (module != null) {
      return module.getMid();
    }

    return null;
  }

  public String getSid(String moduleIdx) {
    Module module = this.context.get(moduleIdx);
    if (module != null) {
      return module.getSid();
    }

    return null;
  }

  public String getModuleIdx(String mid, String sid) {
    int index = this.idIndex.indexOf(mid + "+" + sid);
    if (index > -1) {
      return this.moduleIdxIndex.get(index);
    }
    return null;
  }

  public synchronized void set(Module... moduleDetails) {
    this.set(Arrays.asList(moduleDetails));
  }

  public synchronized void set(List<Module> moduleDetails) {
    for(Module module: moduleDetails) {
      this.context.put(module.getModuleIdx(), module);
    }

    this.reload();
  }

  public synchronized void put(Module module) {
    this.context.put(module.getModuleIdx(), module);
  }

  // merge 기능은 frontend 에서 처리한다.

  public synchronized void del(String moduleIdx) {
    this.context.remove(moduleIdx);
    this.reload();
  }

  public synchronized void reset() {
    this.context = new HashMap<>();
    this.moduleIdxIndex.clear();
    this.idIndex.clear();
  }
}
