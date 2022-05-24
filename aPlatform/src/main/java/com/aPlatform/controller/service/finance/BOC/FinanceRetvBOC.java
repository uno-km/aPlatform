package com.aPlatform.controller.service.finance.BOC;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.file.SO.FileUploadBO;
import com.aPlatform.controller.service.finance.BO.FinanceRetvBO;
@Service
public class FinanceRetvBOC
{
	@Autowired
	FinanceRetvBO financeRetvBO;
	@Autowired
	FileUploadBO fileUploadBO;

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

	public ResponseEntity<String> execlDataFileInsert(final MultipartFile file) throws Exception
	{
		return this.fileUploadBO.uploadExcel(file);
	}
	public ResponseEntity<String> uploadExeclDataFile()
	{
		return this.financeRetvBO.excelInsert();
	}
}
