package com.aPlatform.controller.main.SO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/test")
public class TestPageSO
{
	@RequestMapping(method = RequestMethod.GET)
	public String testPage()
	{
		return "test/TestPage";
	}
	
}
