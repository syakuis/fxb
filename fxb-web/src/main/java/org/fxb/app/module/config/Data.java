package org.fxb.app.module.config;

import org.fxb.web.module.CreateModule;
import org.fxb.web.module.annotation.Created;
import org.fxb.web.module.model.Module;
import org.fxb.web.module.model.ModuleDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 16.
 */
@Created
public class Data implements CreateModule {
  private String name;

  public Data() {
    this.name = "module";
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public Module value() {
    return new ModuleDetails("module", "module", "module");
  }
}
