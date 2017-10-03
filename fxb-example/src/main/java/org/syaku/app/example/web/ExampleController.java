package org.syaku.app.example.web;

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
@RequestMapping("/example")
public class ExampleController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView dispView() {
		ModelAndView mav = new ModelAndView("app/example/example");
		return mav;
	}
}
