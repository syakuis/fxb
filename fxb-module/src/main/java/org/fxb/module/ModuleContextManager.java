package org.fxb.module;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * {@link ModuleDetails} 를 관리한다.
 * todo thread safe coding
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 1. 19.
 */
public class ModuleContextManager {
  private final Logger logger = LoggerFactory.getLogger(ModuleContextManager.class);
  private final static Map<String, Module> context = new HashMap<>();

  public void addModule(Module module) {
    Assert.notNull(module, "module must not be empty");
    Assert.hasText(module.getModuleName(), "moduleName must not be empty");
    Assert.hasText(module.getModuleId(), "moduleId must not be empty");
    this.context.put(module.getModuleId(), module);
  }

  public Module getModule(String moduleId) {
    if (!this.context.containsKey(moduleId)) {
      logger.debug("the moduleId is not found.");
      return null;
    }
    return SerializationUtils.clone((AbstractModule) this.context.get(moduleId));
  }

  public List<Module> toList() {
    return this.context.values().stream().collect(Collectors.collectingAndThen(Collectors.toList(),
        Collections::unmodifiableList));
  }

  public boolean isModuleId(String moduleId) {
    return this.context.containsKey(moduleId);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.context);
  }

  @Override
  public boolean equals(Object target) {
    if (target == this.context) {
      return true;
    }

    if (!(target instanceof Map)) {
      return false;
    }

    Map<String, Module> object = (Map<String, Module>) target;

    return Objects.equals(object, this.context);
  }

  @Override
  public String toString() {
    return this.context.toString();
  }
}
