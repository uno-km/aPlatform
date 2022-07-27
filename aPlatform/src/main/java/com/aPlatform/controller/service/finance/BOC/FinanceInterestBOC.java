package com.aPlatform.controller.service.finance.BOC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.common.model.CommonOutVO;
import com.aPlatform.controller.service.finance.BO.FinanceInterestBO;
import com.aPlatform.controller.service.finance.VO.FinanceInDTO;
import com.aPlatform.controller.service.finance.VO.FinanceVO;
@Service
public class FinanceInterestBOC
{
	@Autowired
	FinanceInterestBO financeInterestBO;
	public CommonOutVO insertUserInterestShare(final FinanceInDTO inDTO)
	{
		CommonOutVO commonOutVO = new CommonOutVO();
		FinanceVO finVO = new FinanceVO();
		finVO.setUserId(inDTO.getUserId());
		finVO.setFinCode(inDTO.getCode());
		finVO.setFinName(inDTO.getFinName());
		finVO.setExchange(inDTO.getExchange());
		finVO.setInterestYn(inDTO.getInterestYn());
		// financeRetvBO.insertUserInterestShare(commonOutVO, finVO);
		financeInterestBO.insertUserInterestShare(commonOutVO, finVO);
		return commonOutVO;
	}
	public CommonOutVO getUserInterestShares()
	{
		return this.financeInterestBO.getUserInterestShares();
	}
}
