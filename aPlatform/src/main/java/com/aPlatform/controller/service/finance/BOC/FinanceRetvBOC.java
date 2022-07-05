package com.aPlatform.controller.service.finance.BOC;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.common.model.CommonOutVO;
import com.aPlatform.controller.file.SO.FileUploadBO;
import com.aPlatform.controller.service.finance.BO.FinanceRetvBO;
import com.aPlatform.controller.service.finance.VO.FinanceVO;
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
	public CommonOutVO insertUserInterestShare(final Map<String, String> param)
	{
		CommonOutVO commonOutVO = new CommonOutVO();
		FinanceVO finVO = new FinanceVO();
		finVO.setExchange(param.get("exchange"));
		finVO.setFinCode(param.get("finCode"));
		finVO.setFinName(param.get("finName"));
		finVO.setUserId(param.get("userId"));
		finVO.setInterestYn(param.get("interestYn"));
		// financeRetvBO.insertUserInterestShare(commonOutVO, finVO);
		financeRetvBO.insertUserInterestShare(commonOutVO, param);
		return commonOutVO;
	}
	public CommonOutVO getUserInterestShares()
	{
		return this.financeRetvBO.getUserInterestShares();
	}
}
