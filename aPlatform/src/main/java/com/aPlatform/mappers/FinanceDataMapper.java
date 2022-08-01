package com.aPlatform.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aPlatform.controller.service.finance.VO.FinanceInDTO;
import com.aPlatform.controller.service.finance.VO.FinanceVO;

@Mapper
public interface FinanceDataMapper
{
	/* 전체 종목코드 호출 */
	abstract public List<FinanceVO> getAllCode();
	/* 주식정보를 가져올 url 정보를 가져온다. */
	abstract public List<FinanceVO> getMappingUrl();
	/* html을 파싱할 각 인자들을 가져온다 */
	abstract public List<FinanceVO> getMappingPharse();
	/* 종목최진화 / 단건저장 */
	abstract public void insertSharesInfo(final FinanceVO inVO);
	abstract public void updateUserInterest(final FinanceInDTO inDto);
	abstract public void deleteUserInterest(final FinanceInDTO inDto);
	/* 기존 전종목 모두 삭제 */
	abstract public void deleteAllDataInShareTable();
	/* 사용자 관심종목 저장 */
	abstract public void insertUserInterest(final FinanceVO inDTO);

	abstract public List<FinanceVO> getUserInterest(final String userId);
}
