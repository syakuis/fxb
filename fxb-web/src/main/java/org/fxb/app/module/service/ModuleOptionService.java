package org.fxb.app.module.service;

import org.fxb.app.module.domain.ModuleOptionEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 12. 1.
 */
@Transactional(readOnly = true)
public interface ModuleOptionService {
  List<ModuleOptionEntity> getModuleOptions(String moduleIdx);
}
