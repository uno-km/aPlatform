package com.aPlatform.controller.service.finance.BOC;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.common.model.commonOutVO;
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
	public commonOutVO execlDataFileInsert(final MultipartFile file) throws Exception
	{
		return this.fileUploadBO.uploadExcelData(file);
	}
}
