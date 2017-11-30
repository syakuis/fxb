package org.fxb.app.module.mybatis;

import org.fxb.app.module.domain.ModuleOptions;
import org.fxb.app.module.mybatis.ModuleOptionsMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 모듈 옵션 데이터를 일괄적으로 관리하는 클래스이다.
 *
 * 클라이언트에서 요청되는 데이터는 모듈 하나에 대한 온전한 전체 데이터여야 한다.
 * moduleOptionsSrl 이 없는 경우 새로운 데이터로 처리한다. 반대인 경우 수정으로 처리된다.
 * 입력된 데이터와 기존 데이터와 비교해서 입력되지 않은 데이터는 제거된다.
 * 클라이언트에서는 기존 데이터를 가져와 자바스크립트를 사용하여 데이터를 유지해야 한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Repository
public class ModuleOptionsBatchDAO {
  @Resource(name = "moduleOptionsMapper")
  private ModuleOptionsMapper moduleOptionsMapper;

  /**
   * 일괄적으로 모듈 옵션을 수정하거나 추가할때는 입력된 데이터를 제외하고 기존 데이터는 삭제된다.
   * 저장하기전에 기존 데이터를 병합하고 데이터를 입력하여야 한다.
   * @param moduleIdx
   * @param moduleOptions
   * @param autoDelete 기존 데이터는 있는 데 입력된 moduleOptions null 이거나 비어있으면 기존 데이터를 모두 삭제한다. default = false
   * @return
   */
  public List<ModuleOptions> save(String moduleIdx, List<ModuleOptions> moduleOptions, boolean autoDelete) {
    Assert.notNull(moduleIdx, "The class must not be null");

    List<ModuleOptions> result = new ArrayList<>();

    if ((moduleOptions == null || moduleOptions.isEmpty()) && autoDelete) {
      moduleOptionsMapper.deleteByModuleIdx(moduleIdx);
      return result;
    }

    // 수정된 번호
    List<Long> moduleOptionsSrl = new ArrayList<>();

    int loop = moduleOptions != null && !moduleOptions.isEmpty() ? moduleOptions.size() : 0;

    for (int i = 0; i < loop; i++) {
      ModuleOptions  options = moduleOptions.get(i);
      options.setModuleIdx(moduleIdx);
      options.setOrder(i);

      if (options.getModuleOptionsSrl() == null) {
        moduleOptionsMapper.insert(options);
      } else {
        moduleOptionsMapper.update(options);
      }

      result.add(options);
    }

    // 수정된 번호가 있으면 제거
    if (moduleOptionsSrl != null && !moduleOptionsSrl.isEmpty()) {
      this.deleteByNotModuleOptionsSrl(moduleIdx, moduleOptionsSrl);
    }

    return result;
  }

  // 수정된 번호를 제외한 번호 삭제
  private List<Long> deleteByNotModuleOptionsSrl(String moduleIdx, List<Long> moduleOptionsSrl) {
    List<Long> moduleOptionsSrlOriginal = new ArrayList<>();

    List<ModuleOptions> moduleOptions = moduleOptionsMapper.selectByModuleIdx(moduleIdx);

    for (ModuleOptions options : moduleOptions) {
      moduleOptionsSrlOriginal.add(options.getModuleOptionsSrl());
    }

    // 수정된 번호 제외하기
    moduleOptionsSrlOriginal.removeAll(moduleOptionsSrl);

    for (Long srl : moduleOptionsSrlOriginal) {
      moduleOptionsMapper.deleteByModuleOptionsSrl(moduleIdx, srl);
    }

    return moduleOptionsSrlOriginal;
  }

}
