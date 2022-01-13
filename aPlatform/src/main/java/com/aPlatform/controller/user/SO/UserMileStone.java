package com.aPlatform.controller.user.SO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping
public class UserMileStone
{
	@GetMapping("/all")
	public String doAll()
	{
		log.info("logined member");
		return "all";
	}
	@GetMapping("/member")
	public void doMember()
	{
		log.info("member member");
	}
	@GetMapping("/manager")
	public void doManager()
	{
		log.info("manager member");
	}
	@GetMapping("/customLogin")
	public void login(String error, String logout, Model model)
	{
		if(error != null)
		{
			model.addAttribute("error", "Login Error Check Your Account");
		}
		if(logout != null)
		{
			model.addAttribute("logout", "Logout!!");
		}
	}
}
