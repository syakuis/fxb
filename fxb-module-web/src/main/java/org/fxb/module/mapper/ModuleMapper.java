package org.fxb.module.mapper;

import org.fxb.context.mybatis.annotation.Mapper;
import org.fxb.module.dao.ModuleDAO;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Mapper("moduleDAO")
public interface ModuleMapper extends ModuleDAO {
}