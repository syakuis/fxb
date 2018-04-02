package org.fxb.module.test;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import org.fxb.module.AbstractModule;
import org.fxb.module.ModuleContext;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 3. 30.
 */
@ModuleContext
@ToString
public class ModuleBean extends AbstractModule {
  @Getter
  private List<String> options;
  public ModuleBean() {
    super("test", "test");
    this.options = Arrays.asList("1", "2");
  }
}