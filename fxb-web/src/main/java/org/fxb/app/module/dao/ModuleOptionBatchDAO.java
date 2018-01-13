package org.fxb.app.module.dao;

import org.fxb.app.module.domain.ModuleOption;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 모듈 옵션 데이터를 일괄적으로 관리하는 클래스이다.
 *
 * 클라이언트에서 요청되는 데이터는 모듈 하나에 대한 온전한 전체 데이터여야 한다.
 * moduleOptionSrl 이 없는 경우 새로운 데이터로 처리한다. 반대인 경우 수정으로 처리된다.
 * 입력된 데이터와 기존 데이터와 비교해서 입력되지 않은 데이터는 제거된다.
 * 클라이언트에서는 기존 데이터를 가져와 자바스크립트를 사용하여 데이터를 유지해야 한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
public class ModuleOptionBatchDAO {
  private ModuleOptionDAO moduleOptionDAO;

  public void setModuleOptionDAO(ModuleOptionDAO moduleOptionDAO) {
    this.moduleOptionDAO = moduleOptionDAO;
  }

  /**
   * 일괄적으로 모듈 옵션을 수정하거나 추가할때는 입력된 데이터를 제외하고 기존 데이터는 삭제된다.
   * 저장하기전에 기존 데이터를 병합하고 데이터를 입력하여야 한다.
   * @param moduleIdx
   * @param moduleOptions
   * @param autoDelete 기존 데이터는 있는 데 입력된 moduleOptions null 이거나 비어있으면 기존 데이터를 모두 삭제한다. default = false
   * @return
   */
  public List<ModuleOption> save(String moduleIdx, List<ModuleOption> moduleOptions, boolean autoDelete) {
    Assert.notNull(moduleIdx, "The class must not be null");

    List<ModuleOption> result = new ArrayList<>();

    if ((moduleOptions == null || moduleOptions.isEmpty()) && autoDelete) {
      moduleOptionDAO.deleteByModuleIdx(moduleIdx);
      return result;
    }

    // 수정된 번호
    List<Long> moduleOptionSrl = new ArrayList<>();

    int loop = moduleOptions != null && !moduleOptions.isEmpty() ?
      moduleOptions.size() : 0;

    for (int i = 0; i < loop; i++) {
      ModuleOption moduleOption = moduleOptions.get(i);
      moduleOption.setModuleIdx(moduleIdx);
      moduleOption.setOrder(i);

      if (moduleOption.getModuleOptionSrl() == null) {
        moduleOptionDAO.insert(moduleOption);
      } else {
        moduleOptionDAO.update(moduleOption);
      }

      moduleOptionSrl.add(moduleOption.getModuleOptionSrl());

      result.add(moduleOption);
    }

    // 수정된 번호가 있으면 제거
    if (moduleOptionSrl != null && !moduleOptionSrl.isEmpty()) {
      this.deleteByNotModuleOptionSrl(moduleIdx, moduleOptionSrl);
    }

    return result;
  }

  // 수정된 번호를 제외한 번호 삭제
  private List<Long> deleteByNotModuleOptionSrl(String moduleIdx, List<Long> moduleOptionSrl) {
    List<Long> moduleOptionSrlOriginal = new ArrayList<>();

    List<ModuleOption> moduleOptionEntities = moduleOptionDAO.findByModuleIdx(moduleIdx);

    for (ModuleOption moduleOptionEntity : moduleOptionEntities) {
      moduleOptionSrlOriginal.add(moduleOptionEntity.getModuleOptionSrl());
    }

    // 수정된 번호 제외하기
    moduleOptionSrlOriginal.removeAll(moduleOptionSrl);

    for (Long srl : moduleOptionSrlOriginal) {
      moduleOptionDAO.deleteByModuleOptionSrl(moduleIdx, srl);
    }

    return moduleOptionSrlOriginal;
  }

}
