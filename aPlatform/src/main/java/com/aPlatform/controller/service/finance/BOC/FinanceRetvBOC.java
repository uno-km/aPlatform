package com.aPlatform.controller.service.finance.BOC;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.common.model.CommonOutVO;
import com.aPlatform.controller.file.BO.FileUploadBO;
import com.aPlatform.controller.service.finance.BO.FinanceRetvBO;
@Service
public class FinanceRetvBOC
{
	@Autowired
	private FinanceRetvBO financeRetvBO;
	@Autowired
	private FileUploadBO fileUploadBO;

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
	public CommonOutVO execlDataFileInsert(final MultipartFile file) throws Exception
	{
		return this.fileUploadBO.uploadExcelData(file);
	}
}
