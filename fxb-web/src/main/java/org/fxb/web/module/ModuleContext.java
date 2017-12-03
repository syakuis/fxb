package org.fxb.web.module;

import lombok.ToString;
import org.fxb.web.module.model.Module;
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

  // 서비스 ...
  private boolean isReloaded;

  private String getIdIndex(String mid, String sid) {
    return new StringBuffer(mid).append("+").append(sid).toString();
  }

  /**
   * context id index 를 초기화 한다.
   */
  private synchronized void indexReset() {
    this.moduleIdxIndex.clear();
    this.idIndex.clear();

    for(Map.Entry<String, Module> entry : this.context.entrySet()) {
      Module moduleDetails = entry.getValue();
      this.moduleIdxIndex.add(moduleDetails.getModuleIdx());
      this.idIndex.add(getIdIndex(moduleDetails.getMid(), moduleDetails.getSid()));
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
    if (module == null) {
      return null;
    }

    return module.getMid();
  }

  public String getSid(String moduleIdx) {
    Module module = this.context.get(moduleIdx);
    if (module == null) {
      return null;
    }

    return module.getSid();
  }

  public String getModuleIdx(String mid, String sid) {
    int index = this.idIndex.indexOf(getIdIndex(mid, sid));
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

    this.indexReset();
  }

  public synchronized void put(Module module) {
    this.context.put(module.getModuleIdx(), module);
  }

  public synchronized void del(String moduleIdx) {
    this.context.remove(moduleIdx);
    this.indexReset();
  }

  public synchronized void reset() {
    this.context = new HashMap<>();
    this.moduleIdxIndex.clear();
    this.idIndex.clear();
  }
}
