package org.syaku.app.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 20.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView dispDemoView() {
		ModelAndView mav = new ModelAndView("app/demo/demo.view");
		return mav;
	}
}
