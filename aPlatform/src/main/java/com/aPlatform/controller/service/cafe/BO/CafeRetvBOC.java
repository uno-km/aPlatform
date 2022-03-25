package com.aPlatform.controller.service.cafe.BO;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class CafeRetvBOC
{

	public ModelAndView reternMainPage()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cafe/index");
		return mv;
	}
}
