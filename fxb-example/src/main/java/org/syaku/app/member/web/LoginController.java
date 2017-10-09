package org.syaku.app.member.web;

import org.fxb.commons.web.servlet.handlers.Done;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 10. 7.
 */
@Controller
@RequestMapping("/member")
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String dispLogin(Model model) {
		return "app/member/login";
	}
}
