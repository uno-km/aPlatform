package com.aPlatform.controller.user.SO;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aPlatform.controller.user.BOC.LoginBOC;
import com.aPlatform.controller.user.VO.UserinfoOutVO;
import com.aPlatform.controller.user.VO.UserinfoVO;

@Controller
@RequestMapping(value = "/user")
public class UserActiveSO
{
	@Autowired
	LoginBOC loginDAO;
	@RequestMapping(method = RequestMethod.GET, value = "/signup")
	public boolean register(@RequestBody UserinfoVO userinfoVO)
	{
		return loginDAO.registerUser(userinfoVO);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/checkid")
	public boolean checkDuplId(@RequestBody UserinfoVO userinfoVO)
	{
		System.out.println(loginDAO.checkDuplId(userinfoVO));
		return loginDAO.checkDuplId(userinfoVO);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/signin")
	public ResponseEntity<UserinfoOutVO> signinUser(
			@RequestBody UserinfoVO userinfoVO, HttpSession session)
	{
		UserinfoOutVO outVO = new UserinfoOutVO();
		outVO = loginDAO.signinUser(userinfoVO, session);
		return outVO != null
				? new ResponseEntity<>(outVO, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/customLogin")
	public String login(String error, String logout, Model model)
	{
		if(error != null)
		{
			model.addAttribute("error", "Login Error Check Your Account");
		}
		if(logout != null)
		{
			model.addAttribute("logout", "Logout!!");
		}
		return "loginPage";
	}

}
