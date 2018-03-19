package org.fxb.module.web;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.fxb.module.TestConfiguration;
import org.fxb.module.service.ModuleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 3. 6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RestModuleControllerTest extends TestConfiguration {
  private MockMvc mockMvc;

  @Autowired
  ModuleService moduleService;

  @Before
  public void setup() {
    RestModuleController rest = new RestModuleController();
    rest.setModuleService(moduleService);
    this.mockMvc = MockMvcBuilders.standaloneSetup(rest)
        .alwaysExpect(status().isOk())
        .alwaysExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .build();
  }

  @Test
  public void test() throws Exception {
    mockMvc.perform(get("/api/module").accept(MediaType.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(4)));
  }
}
