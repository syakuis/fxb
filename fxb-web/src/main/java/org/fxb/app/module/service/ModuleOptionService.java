package org.fxb.app.module.service;

import org.fxb.app.module.domain.ModuleOptionEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 12. 1.
 */
@Transactional(readOnly = true)
public interface ModuleOptionService {
  @Cacheable( cacheNames = "fxb.module", key = "'moduleOptions'.concat(#moduleIdx)", condition = "#moduleIdx != null", sync = true)
  List<ModuleOptionEntity> getModuleOptions(String moduleIdx);
}
