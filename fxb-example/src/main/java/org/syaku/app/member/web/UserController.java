package org.syaku.app.member.web;

import org.fxb.commons.web.servlet.handlers.Done;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 10. 20.
 */
@Controller
@RequestMapping("/member")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public Done<Map<String, Object>> getUser(Authentication authentication) {
		Map<String, Object> data = new HashMap<>();
		if (authentication != null) {
			data.put("principal", authentication.getPrincipal());
			data.put("details", authentication.getDetails());
			data.put("credentials", authentication.getCredentials());
		}
		return new Done(data);
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/user2", method = RequestMethod.GET)
	@ResponseBody
	public Done<Map<String, Object>> getUser2(Authentication authentication) {
		Map<String, Object> data = new HashMap<>();
		if (authentication != null) {
			data.put("principal", authentication.getPrincipal());
			data.put("details", authentication.getDetails());
			data.put("credentials", authentication.getCredentials());
		}
		return new Done(data);
	}
}
