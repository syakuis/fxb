package org.syaku.app.member.web;

import org.apache.commons.lang3.StringUtils;
import org.fxb.commons.web.servlet.handlers.Done;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 10. 24.
 */
@Controller
@RequestMapping("/member")
public class AuthenticationController {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private WebInvocationPrivilegeEvaluator privilegeEvaluator;

	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	@ResponseBody
	public Done getAuthentication(
			Authentication authentication,
			@RequestParam(value = "url", required = false) String url
	) {

		Map<String, Object> data = new HashMap<>();
		data.put("principal", null);
		data.put("details", null);
		data.put("credentials", null);
		data.put("username", null);
		data.put("isAllowed", false);
		data.put("isAnonymous", authentication == null);
		if (authentication != null) {
			data.put("username", authentication.getName());
			data.put("principal", authentication.getPrincipal());
			data.put("details", authentication.getDetails());
			data.put("credentials", authentication.getCredentials());
		}

		if (StringUtils.isNotEmpty(url)) {
			data.put("isAllowed", privilegeEvaluator.isAllowed(url, authentication));
		}

		return new Done(data);
	}
}
