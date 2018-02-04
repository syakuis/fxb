package org.fxb.web.module;

import lombok.ToString;
import org.fxb.web.module.model.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Map;

/**
 * module 데이터를 공유하기 위한 저장소.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
@ToString
public class ModuleContext implements Serializable {
  private static final Logger logger = LoggerFactory.getLogger(ModuleContext.class);
  private static final long serialVersionUID = -1429812913792066578L;
}
