package org.fxb.app.module.web;

import org.fxb.app.module.domain.Module;
import org.fxb.app.module.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 8.
 */
@RestController
@RequestMapping("/api/module")
public class ModuleController {
  @Autowired
  ModuleService moduleService;

  @GetMapping("")
  public List<Module> getModuleList() {
    return moduleService.getModules();
  }
}
