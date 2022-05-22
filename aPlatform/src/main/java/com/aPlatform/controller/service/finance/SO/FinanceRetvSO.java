package com.aPlatform.controller.service.finance.SO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/service/finance")
public class FinanceRetvSO
{

	@GetMapping(value = "/excelUploadPopup")
	private ModelAndView returnPopupExcelUpload()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("finance/popup/excelUploadPopup");
		return mv;
	}
}
