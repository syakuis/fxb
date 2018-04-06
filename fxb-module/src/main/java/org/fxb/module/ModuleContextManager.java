package org.fxb.module;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 생성된 {@link Module} 을 컨텍스트에서 관리한다.
 * todo thread safe coding
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 1. 19.
 */
public class ModuleContextManager {
  private final Logger logger = LoggerFactory.getLogger(ModuleContextManager.class);
  private Map<String, Module> context = new ConcurrentHashMap<>();

  public void remove(String moduleId) {
    if (logger.isDebugEnabled()) {
      if (this.context.containsKey(moduleId)) {
        logger.debug("remove - {} remove moduleContext.", moduleId);
      } else {
        logger.warn("remove - {} moduleId not found.", moduleId);
      }
    }

    this.context.remove(moduleId);
  }

  public void remove(List<String> moduleIds) {
    for (String moduleId : moduleIds) {
      this.remove(moduleId);
    }
  }

  public void add(Module module) {
    Assert.notNull(module, "add - module must not be empty");
    Assert.hasText(module.getModuleName(), "add - moduleName must not be empty");
    Assert.hasText(module.getModuleId(), "add - moduleId must not be empty");

    String moduleId = module.getModuleId();
    Module currentModule = this.context.get(moduleId);

    if (currentModule == null) {
     this.context.put(moduleId, module);
     logger.debug("add - {} add moduleContext.", moduleId);
    } else if (!currentModule.isImmutable()) {
     this.context.put(moduleId, module);
     logger.debug("add - {} overwrite moduleContext.", moduleId);
    } else {
      logger.warn("add - Can not add. {} module Immutable or unknown", moduleId);
    }
  }

  public Module get(String moduleId) {
    if (!this.context.containsKey(moduleId)) {
      logger.warn("get - {} moduleId not found.", moduleId);
      return null;
    }

    return this.context.get(moduleId);
  }

  public <T extends Module> T get(String moduleId, Class<T> type) {
    if (!this.context.containsKey(moduleId)) {
      logger.warn("get - {} moduleId not found.", moduleId);
      return null;
    }

    return type.cast(SerializationUtils.clone(this.context.get(moduleId)));
  }

  public List<Module> toList() {
    return this.context.values().stream().collect(Collectors.collectingAndThen(Collectors.toList(),
        Collections::unmodifiableList));
  }

  public boolean isModuleId(String moduleId) {
    return this.context.containsKey(moduleId);
  }

  private Map<String, Module> getContext() {
    return this.context;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.context);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }

    if (object instanceof ModuleContextManager) {
      return this.context.equals(((ModuleContextManager) object).getContext());
    }

    return false;
  }

  @Override
  public String toString() {
    return this.context.toString();
  }
}
