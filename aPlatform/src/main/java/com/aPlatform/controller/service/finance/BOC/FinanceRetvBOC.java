package com.aPlatform.controller.service.finance.BOC;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.service.finance.BO.FinanceRetvBO;
import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
@Service
public class FinanceRetvBOC
{
	@Autowired
	FinanceRetvBO financeRetvBO;

	public Map<String, String> getCodeMap()
	{
		return financeRetvBO.getCodeMap();
	}
	public ModelAndView reternMainPage()
	{
		FinanceDataMatrix.getInstance();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("finance/index");
		return mv;
	}
}
