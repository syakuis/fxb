package org.syaku.app.example.web;

import org.fxb.web.module.ModuleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 8.
 */
@Controller
@RequestMapping("/api/example")
public class ApiController {
  @Autowired
  ModuleContext moduleContext;
  @GetMapping
  @ResponseBody
  public String getApiExample() {
    moduleContext.get();
    return "good";
  }
}
