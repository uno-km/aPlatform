package com.aPlatform.controller.service.finance.BO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aPlatform.controller.common.model.CommonOutVO;
import com.aPlatform.controller.common.model.ResultDTO;
import com.aPlatform.controller.service.finance.VO.FinanceVO;
import com.aPlatform.controller.service.finance.model.ExcelData;
import com.aPlatform.mappers.FinanceDataMapper;
import com.aPlatform.utils.Utils;

@Service
public class FinanceRetvBO
{
	@Autowired
	private FinanceDataMapper financeDataMapper;
	@Autowired
	private ExcelData excelData;
	
	public Map<String, String> getCodeMap()
	{
		Map<String, String> outMap = new HashMap<>();
		List<FinanceVO> innerList = this.financeDataMapper.getAllCode();
		for (FinanceVO finVO : innerList) outMap.put(finVO.getFinName(), finVO.getFinCode());
		return outMap;
	}
	
	public synchronized CommonOutVO excelInsert(final MultipartFile file)
	{
		CommonOutVO commonoutVO = new CommonOutVO();
		ResultDTO result = new ResultDTO();
		commonoutVO.setResultDTO(result);
		try
		{
			try
			{
				/* 기존 SHARES_FIN_INFO 의 모든 종목을 삭제한다. - 테이블 데이터 초기화 */
				this.financeDataMapper.deleteAllDataInShareTable();
			}
			catch (Exception e)
			{
				/* 해당 에러 발생히 웹단으로 500 에러메세지 응답 */
				commonoutVO.setError(e.getMessage());
				result.setCodeMessage("500", "알수없는 오류가 발생했습니다. 관리자에게 문의하세요.");
				return commonoutVO;
			}
			this.excelData.insertExcelData(commonoutVO, Utils.file.multipartFileToFile(file));
			/* 성공하면 200 응답. */
			return commonoutVO;
		}
		catch (Exception e)
		{
			/* 중간에러발생시 500 응답. */
			commonoutVO.setError(e.getMessage());
			result.setCodeMessage("500", "알수없는 오류가 발생했습니다. 관리자에게 문의하세요.");
			return commonoutVO;
		}
	}
}
