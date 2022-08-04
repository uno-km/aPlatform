package com.aPlatform.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aPlatform.controller.service.finance.VO.FinanceInDTO;
import com.aPlatform.controller.service.finance.VO.FinanceVO;

@Mapper
public interface FinanceUserInterestMapper
{
	/* 사용자 관심종목 저장 */
	abstract public void insertUserInterest(final FinanceVO inDTO);
	/* 사용자 관심종목 저장 */
	abstract public void updateUserInterest(final FinanceInDTO inDto);
	/* 사용자 관심종목 저장 */
	abstract public void deleteUserInterest(final FinanceInDTO inDto);

	abstract public List<FinanceVO> getUserInterest(final String userId);
}
