package org.fxb.module.web;

import java.util.List;
import org.fxb.module.domain.ModuleEntity;
import org.fxb.module.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 3. 6.
 */
@RestController
@RequestMapping("/api/module")
public class RestModuleController {
  private ModuleService moduleService;
  @Autowired
  public void setModuleService(@Qualifier("moduleService") ModuleService moduleService) {
    Assert.notNull(moduleService, "The moduleService argument is required; it must not be null");
    this.moduleService = moduleService;
  }

  @GetMapping(value = "")
  public List<ModuleEntity> getModules() {
    return moduleService.getModules();
  }
}
