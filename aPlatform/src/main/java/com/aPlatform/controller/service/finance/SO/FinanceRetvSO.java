package com.aPlatform.controller.service.finance.SO;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/service/finance")
public class FinanceRetvSO
{
	@GetMapping(value = "/main")
	private ModelAndView reternMainPage(Model model)
	{
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("finance/index");
		return modelandview;
	}
}
