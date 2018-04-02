package org.fxb.module;

import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 3. 30.
 */
@Getter
@ToString
public abstract class AbstractModule implements Module, Serializable {
  private static final long serialVersionUID = 4963113037670019088L;
  private final String moduleName;
  private final String moduleId;

  /**
   * ModuleContext 를 생성할때 설정한 파라메터는 수정할 수 없다.
   * @param moduleName 중복가능 한 모듈명을 가진다. 그룹과 같은 역활을 한다.
   * @param moduleId 유일한 모듈명을 가진다.
   */
  public AbstractModule(String moduleName, String moduleId) {
    this.moduleName = moduleName;
    this.moduleId = moduleId;
  }

  public <T extends Module> T getObject(Class<T> type) {
    if (this == null) {
      return null;
    }

    if (!type.isInstance(this)) {
      throw new ClassCastException();
    }

    return type.cast(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(moduleName, moduleId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Module)) {
      return false;
    }

    Module newObject = (Module) obj;

    if (Objects.equals(newObject.getModuleName(), moduleName) &&
        Objects.equals(newObject.getModuleId(), moduleId)) {
      return true;
    }

    return false;
  }
}
