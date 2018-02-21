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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
   * moduleId 가 같으면 moduleIdx 가 같은 것만 수정할 수 있다.
   * @param module
   */
  public synchronized Module addModule(Module module) {
    return sync(module, 0);
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

  public synchronized Module getModule(String moduleId) {
    return sync(new ModuleDetails(moduleId, moduleId), 1);
  }

  private Module sync(Module module, int type) {
    synchronized (context) {
      if (type == 0) {
        Assert.hasText(module.getModuleId(), "name must not be empty");

        if (context.containsKey(module.getModuleId())) {
          Module original = context.get(module.getModuleId());
          if (!original.getModuleIdx().equals(module.getModuleIdx())) {
            throw new IllegalArgumentException("the module is exists");
          }
        }

        context.put(module.getModuleId(), module);
      }

      return SerializationUtils.clone((ModuleDetails) context.get(module.getModuleId()));
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
    synchronized (this.context) {
      if (obj == this.context) {
        return true;
      }

      if (!(obj instanceof Map)) {
        return false;
      }

      Map<String, Module> o = (Map<String, Module>) obj;

      return o.equals(this.context);
    }
  }

  @Override
  public String toString() {
    synchronized (this.context) {
      return this.context.toString();
    }
  }
}
