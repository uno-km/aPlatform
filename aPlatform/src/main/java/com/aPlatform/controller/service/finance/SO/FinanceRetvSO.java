package com.aPlatform.controller.service.finance.SO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/service/finance")
public class FinanceRetvSO
{
	@GetMapping(value = "/main")
	private String reternMainPage()
	{
		return "main";
	}
}
