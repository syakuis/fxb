package org.fxb.app.module.domain;

import org.fxb.web.module.CreateModule;
import org.fxb.web.module.annotation.Created;
import org.fxb.web.module.model.Module;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 16.
 */
@Created
public class Data implements CreateModule {
  @Override
  public String name() {
    return "module";
  }

  @Override
  public Module value() {
    return null;
  }
}
