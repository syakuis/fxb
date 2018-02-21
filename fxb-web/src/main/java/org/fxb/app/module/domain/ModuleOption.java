package org.fxb.app.module.domain;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ModuleOption {
  private String moduleId;
  private String name;
  private String value;
  private String title;
  private int order;

  public static List<ModuleOption> createList(ModuleOption... moduleOptions) {
    return Arrays.asList(moduleOptions);
  }
}