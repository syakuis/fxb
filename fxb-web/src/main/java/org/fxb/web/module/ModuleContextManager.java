package org.fxb.web.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ModuleContextManager {
  private static final Logger logger = LoggerFactory.getLogger(ModuleContextManager.class);

  private Map<String, Module> context = new HashMap<>();

  /**
   * @param module
   */
  public void addModule(Module module) {
    if (module == null) throw new IllegalArgumentException("module must not be empty");
    Assert.hasText(module.getModuleId(), "moduleId must not be empty");
    this.context.put(module.getModuleId(), module);
  }

  public void remove(String moduleId) {
    Assert.hasText(moduleId, "moduleId must not be empty");

    this.context.remove(moduleId);
  }

  public void destory() {
    this.context = new HashMap<>();
  }

  public boolean isExists(String moduleId) {
    Assert.hasText(moduleId, "moduleId must not be empty");
    return context.containsKey(moduleId);
  }

  public Map<String, Module> getModule() {
    return new HashMap<>(this.context);
  }

  /**
   * context map to list
   * todo caching
   * @return
   */
  public List<Module> getModules() {
    return new ArrayList<>(context.values());
  }

  public Module getModule(String moduleId) {
    if (!this.context.containsKey(moduleId)) {
      logger.debug("the module is not found.");
      return null;
    }
    return SerializationUtils.clone((ModuleDetails) context.get(moduleId));
  }

  @Override
  public int hashCode() {
    return this.context.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Map)) {
      return false;
    }

    if (obj == this.context) {
      return true;
    }

    Map<String, Module> _obj = (Map<String, Module>) obj;

    return _obj.equals(this.context);
  }

  @Override
  public String toString() {
    return this.context.toString();
  }
}
