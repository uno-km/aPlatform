package com.aPlatform.controller.service.finance.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.common.model.CommonOutVO;
import com.aPlatform.controller.common.model.ResultDTO;
import com.aPlatform.controller.service.finance.VO.FinanceInDTO;
import com.aPlatform.controller.service.finance.VO.FinanceVO;
import com.aPlatform.mappers.FinanceDataMapper;

@Service
public class FinanceInterestBO
{
	@Autowired
	private FinanceDataMapper financeDataMapper;
	public CommonOutVO getUserInterestShares()
	{
		CommonOutVO commonoutVO = new CommonOutVO();
		ResultDTO result = new ResultDTO();
		commonoutVO.setResultDTO(result);
		try
		{
			this.financeDataMapper.getUserInterest(new String("qwe"));
		}
		catch (Exception e)
		{
			commonoutVO.setError(e.getMessage());
			result.setCodeMessage("500", "알수없는 오류가 발생했습니다. 관리자에게 문의하세요.");
			// TODO: handle exception
		}
		return commonoutVO;
	}
	public void insertUserInterestShare(CommonOutVO commonOutVO, final FinanceVO inVO)
	{
		ResultDTO result = new ResultDTO();
		try
		{
			this.financeDataMapper.insertUserInterest(inVO);
			result.setCodeMessage("200", "관심종목 저장이 완료되었습니다.");
		}
		catch (Exception e)
		{
			result.setCodeMessage("500", "알수없는 이유로 저장에 실패했습니다. 다시시도 해주세요.");
		}
		commonOutVO.setResultDTO(result);
	}
	public void deleteUserInterestShare(CommonOutVO commonOutVO, final FinanceInDTO inDto)
	{
		ResultDTO result = new ResultDTO();
		try
		{
			this.financeDataMapper.deleteUserInterest(inDto);
			result.setCodeMessage("200", "관심종목 저장이 완료되었습니다.");
		}
		catch (Exception e)
		{
			result.setCodeMessage("500", "알수없는 이유로 저장에 실패했습니다. 다시시도 해주세요.");
		}
		commonOutVO.setResultDTO(result);
	}
	public void updateUserInterestShare(CommonOutVO commonOutVO, final FinanceInDTO inDto)
	{
		ResultDTO result = new ResultDTO();
		try
		{
			this.financeDataMapper.updateUserInterest(inDto);
			result.setCodeMessage("200", "관심종목 저장이 완료되었습니다.");
		}
		catch (Exception e)
		{
			result.setCodeMessage("500", "알수없는 이유로 저장에 실패했습니다. 다시시도 해주세요.");
		}
		commonOutVO.setResultDTO(result);
	}
}
