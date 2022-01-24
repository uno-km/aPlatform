package com.aPlatform.controller.user.SO;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aPlatform.controller.user.BO.CheckEmailBO;

@Controller
@RequestMapping(value = "/user")
public class CheckEmailSO
{
	@Autowired
	CheckEmailBO checkEmailBO;
	@RequestMapping(method = RequestMethod.GET, value = "/checkEmail")
	public void sendGmail(Model model, HttpServletRequest request)
	{
		String user_email = request.getParameter("user_email");
		checkEmailBO.gmailSend(user_email);
	};
}
