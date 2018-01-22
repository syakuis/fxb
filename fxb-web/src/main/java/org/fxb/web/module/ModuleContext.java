package org.fxb.web.module;

import lombok.ToString;
import org.fxb.web.module.model.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Map;

/**
 * module 데이터를 공유하기 위한 저장소.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
@ToString
public class ModuleContext implements Serializable {
  private static final Logger logger = LoggerFactory.getLogger(ModuleContext.class);
  private static final long serialVersionUID = -1429812913792066578L;

  private final ModuleContextService moduleContextService;

  public ModuleContext(ModuleContextService moduleContextService) {
    this.moduleContextService = moduleContextService;
  }

  public synchronized Map<String, Module> get() {
    return this.moduleContextService.getModuleContext();
  }

  public synchronized Module get(String moduleIdx) {
    return this.get().get(moduleIdx);
  }

  public synchronized String getMid(String moduleIdx) {
    Module module = this.get().get(moduleIdx);
    if (module == null) {
      return null;
    }

    return module.getMid();
  }

  public synchronized String getSid(String moduleIdx) {
    Module module = this.get().get(moduleIdx);
    if (module == null) {
      return null;
    }

    return module.getSid();
  }

  public synchronized String getModuleIdx(String mid, String sid) {
    int index = this.moduleContextService.getModuleIdx().indexOf(moduleContextService.createId(mid, sid));
    if (index > -1) {
      return this.moduleContextService.getModuleIdx().get(index);
    }
    return null;
  }
}
