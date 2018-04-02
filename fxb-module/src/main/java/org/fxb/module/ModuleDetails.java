package org.fxb.module;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 모듈 정보를 담는 다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2017. 11. 21.
 */
@Getter
@ToString
public class ModuleDetails extends AbstractModule {
  private String moduleId;
  private String moduleName;

  @Setter
  private Object details;

  public ModuleDetails(String moduleName, String moduleId) {
    super(moduleName, moduleId);

    this.moduleName = moduleName;
    this.moduleId = moduleId;
  }

  public <T> T getDetails(Class<T> type) {
    if (details == null) {
      return null;
    }

    if (!type.isInstance(details)) {
      throw new ClassCastException();
    }

    return type.cast(details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(moduleName, moduleId, details);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof ModuleDetails)) {
      return false;
    }

    ModuleDetails newObject = (ModuleDetails) obj;

    if (Objects.equals(newObject.getModuleName(), moduleName) &&
        Objects.equals(newObject.getModuleId(), moduleId) &&
        Objects.deepEquals(newObject.getDetails(), details)) {
      return true;
    }

    return false;
  }
}
