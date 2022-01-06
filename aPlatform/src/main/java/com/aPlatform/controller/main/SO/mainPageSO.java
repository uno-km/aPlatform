package com.aPlatform.controller.main.SO;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class mainPageSO
{

	@RequestMapping(method = RequestMethod.GET)
	public String home(Locale locale, Model model)
	{
		return "index";
	}

	@RequestMapping(value = "/favicon.ico", method = RequestMethod.GET)
	public void favicon(HttpServletRequest request, HttpServletResponse reponse)
	{

		try
		{
			reponse.sendRedirect("/resources/assets/favicon.ico");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@PostMapping(value = "loadservice")
	public void serviceList()
	{
		System.out.println("check");
	}
}
