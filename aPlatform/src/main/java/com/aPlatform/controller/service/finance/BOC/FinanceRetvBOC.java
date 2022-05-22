package com.aPlatform.controller.service.finance.BOC;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.service.finance.BO.FinanceRetvBO;
@Service
public class FinanceRetvBOC
{
	@Autowired
	FinanceRetvBO financeRetvBO;

	public ModelAndView reternMainPage()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("finance/index");
		return mv;
	}
	public Map<String, String> getCodeMap()
	{
		return this.financeRetvBO.getCodeMap();
	}

	public ResponseEntity<String> execlDataFileInsert()
	{
		return this.financeRetvBO.excelInsert();
	}
}
