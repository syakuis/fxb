package org.fxb.boot;

import org.fxb.context.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Bootstrapping.class)
@ActiveProfiles({ "test", "mybatis" })
public class BootstrappingTest {

	@Autowired
	Config config;

	@Test
	public void test() {
	}

	// todo Properties Test

	// todo Config Test

	// todo 그외 기본 설정이 잘 구성되었는 지 확인한다. 절대경로

	// todo MessageSource OverWrite Test
}
