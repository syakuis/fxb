package org.fxb.app.module.service;

import org.fxb.app.module.domain.ModuleOptionEntity;
import org.fxb.app.module.mybatis.ModuleOptionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 12. 1.
 */
@Service
public class MyBatisModuleOptionService implements ModuleOptionService {
  @Resource(name = "moduleOptionMapper")
  private ModuleOptionMapper moduleOptionMapper;

  @Override
  public List<ModuleOptionEntity> getModuleOptions(String moduleIdx) {
    return moduleOptionMapper.selectByModuleIdx(moduleIdx);
  }
}
