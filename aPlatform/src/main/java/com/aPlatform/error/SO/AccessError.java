package com.aPlatform.error.SO;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
public class AccessError
{
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model)
	{
		System.out.println("Access Denid : " + auth);
		model.addAttribute("msg", "Access Denied");
	}
}
