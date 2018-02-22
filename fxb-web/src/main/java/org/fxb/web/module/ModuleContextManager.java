package org.fxb.web.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.SerializationUtils;
import org.fxb.web.module.model.Module;
import org.fxb.web.module.model.ModuleDetails;
import org.springframework.util.Assert;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 19.
 */
public class ModuleContextManager implements Serializable {
  private static final long serialVersionUID = -7851817699437417342L;
  private static final Map<String, Module> context = new ConcurrentHashMap<>();

  /**
   * @param module
   */
  public void addModule(Module module) {
    if (module == null) throw new IllegalArgumentException("module must not be empty");
    Assert.hasText(module.getModuleId(), "moduleId must not be empty");

    synchronized (this.context) {
      this.context.put(module.getModuleId(), module);
    }
  }

  public void remove(String moduleId) {
    Assert.hasText(moduleId, "moduleId must not be empty");

    synchronized (this.context) {
      this.context.remove(moduleId);
    }
  }

  public boolean isExists(String moduleId) {
    Assert.hasText(moduleId, "moduleId must not be empty");

    synchronized (this.context) {
      return context.containsKey(moduleId);
    }
  }

  public Map<String, Module> getModule() {
    synchronized(this.context) {
      return new HashMap<>(this.context);
    }
  }

  /**
   * context map to list
   * todo caching
   * @return
   */
  public List<Module> getModules() {
    synchronized (context) {
      return new ArrayList<>(context.values());
    }
  }

  public Module getModule(String moduleId) {
    synchronized (this.context) {
      if (!this.context.containsKey(moduleId)) {
        throw new IllegalArgumentException("the module is not found.");
      }
      return SerializationUtils.clone((ModuleDetails) context.get(moduleId));
    }
  }

  @Override
  public int hashCode() {
    synchronized (this.context) {
      return this.context.hashCode();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Map)) {
      return false;
    }

    synchronized (this.context) {
      if (obj == this.context) {
        return true;
      }

      Map<String, Module> _obj = (Map<String, Module>) obj;

      return _obj.equals(this.context);
    }
  }

  @Override
  public String toString() {
    synchronized (this.context) {
      return this.context.toString();
    }
  }
}
