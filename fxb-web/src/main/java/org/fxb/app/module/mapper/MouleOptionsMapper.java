package org.fxb.app.module.mapper;

import org.fxb.app.module.domain.Module;
import org.fxb.app.module.domain.ModuleOptions;
import org.fxb.config.support.Mapper;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Mapper
public interface MouleOptionsMapper {
  List<Module> selectByModuleId(String moduleIdx);
  void insert(ModuleOptions moduleOptions);
  void update(ModuleOptions moduleOptions);
  void delete(String moduleIdx);
}
