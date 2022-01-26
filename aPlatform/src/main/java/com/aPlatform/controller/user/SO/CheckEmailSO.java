package com.aPlatform.controller.user.SO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aPlatform.controller.user.BOC.OperMailBOC;

@Controller
@RequestMapping(value = "/user")
public class CheckEmailSO
{
	@Autowired
	OperMailBOC operMailBOC;
	@RequestMapping(method = RequestMethod.GET, value = "/checkEmail")
	public @ResponseBody String sendGmail(Model model,
			@RequestParam Map<String, Object> param)
	{
		return operMailBOC.sendMailSetting(param);
	};
}
