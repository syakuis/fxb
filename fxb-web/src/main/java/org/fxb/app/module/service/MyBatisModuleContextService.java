package org.fxb.app.module.service;

import org.fxb.app.module.domain.ModuleEntity;
import org.fxb.app.module.domain.ModuleOptionEntity;
import org.fxb.web.module.ModuleContextService;
import org.fxb.web.module.model.Module;
import org.fxb.web.module.model.ModuleDetails;
import org.fxb.web.module.model.Option;
import org.fxb.web.module.model.OptionDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * ModuleEntity 의 데이터를 Module 에 저장한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
@Service
@Transactional(readOnly = true)
public class MyBatisModuleContextService implements ModuleContextService {
  @Resource(name = "myBatisModuleService")
  ModuleService moduleService;

  @Resource(name = "myBatisModuleOptionService")
  ModuleOptionService moduleOptionService;

  @Override
  public List<String> getModuleIdx() {
    List<String> moduleIdxIndex = new ArrayList<>();

    for(Map.Entry<String, Module> entry : this.getModuleContext().entrySet()) {
      Module moduleDetails = entry.getValue();
      moduleIdxIndex.add(moduleDetails.getModuleIdx());
    }

    return moduleIdxIndex;
  }

  @Override
  public List<String> getId() {
    List<String> idIndex = new ArrayList<>();

    for(Map.Entry<String, Module> entry : this.getModuleContext().entrySet()) {
      Module moduleDetails = entry.getValue();
      idIndex.add(createId(moduleDetails.getMid(), moduleDetails.getSid()));
    }

    return idIndex;
  }

  @Override
  public String createId(String mid, String sid) {
    return new StringBuffer(mid).append("+").append(sid).toString();
  }

  @Override
  public synchronized Map<String, Module> getModuleContext() {
    Map<String, Module> result = new HashMap<>();

    List<ModuleEntity> modules = moduleService.getModules();
    for (ModuleEntity moduleEntity : modules) {
      Module module = new ModuleDetails(
        moduleEntity.getModuleIdx(),
        moduleEntity.getModuleName(),
        moduleEntity.getModuleId(),
        moduleEntity.getBrowserTitle()
      );
      // menuIdx = menu
      // layoutIdx = layout
      module.setSkin(moduleEntity.getSkin());

      List<Option> options = new LinkedList<>();

      List<ModuleOptionEntity> moduleOptions = moduleOptionService.getModuleOptions(moduleEntity.getModuleIdx());
      for (ModuleOptionEntity moduleOption : moduleOptions) {
        Option option = new OptionDetails(
          moduleOption.getName(),
          moduleOption.getValue(),
          moduleOption.getTitle(),
          moduleOption.getOrder()

        );
        options.add(option);
      }
      module.setOptions(ModuleDetails.setOptions(options));

      result.put(module.getModuleIdx(), module);
    }

    return result;
  }
}
