package com.aPlatform.controller.user.SO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aPlatform.controller.user.DAO.LoginDAO;
import com.aPlatform.controller.user.VO.UserinfoVO;

@Controller
@RequestMapping(value = "/user")
public class UserActiveSO
{
	@Autowired
	LoginDAO loginDAO;
	@RequestMapping(method = RequestMethod.GET, value = "/signup")
	public boolean register(@RequestBody UserinfoVO userinfoVO)
	{
		
		return loginDAO.registerUser(userinfoVO);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/checkid")
	public boolean checkDuplId(@RequestBody UserinfoVO userinfoVO)
	{
		return loginDAO.checkDuplId(userinfoVO);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/signin")
	public boolean signinUser(@RequestBody UserinfoVO userinfoVO)
	{
		return loginDAO.signinUser(userinfoVO);
	}
}
