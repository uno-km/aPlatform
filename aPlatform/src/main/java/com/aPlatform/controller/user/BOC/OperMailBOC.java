package com.aPlatform.controller.user.BOC;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.user.BO.CheckEmailBO;
@Service
public class OperMailBOC
{
	@Autowired
	CheckEmailBO checkEmailBO;
	public String sendMailSetting(Map<String, Object> param)
	{
		Map<String, String> inMap = new HashMap<String, String>();
		inMap.put("mail_sbst", (String) param.get("mail_sbst"));
		inMap.put("user_email", (String) param.get("user_email"));
		inMap.put("mail_send_time", (String) param.get("mail_send_time"));
		return checkEmailBO.gmailSend(inMap);
	}
}
