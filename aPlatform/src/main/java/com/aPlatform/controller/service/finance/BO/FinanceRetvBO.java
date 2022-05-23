package com.aPlatform.controller.service.finance.BO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.VO.FinanceVO;
import com.aPlatform.controller.service.finance.model.ExcelData;
import com.aPlatform.mappers.FinanceDataMapper;

@Service
public class FinanceRetvBO
{
	@Autowired
	FinanceDataMapper financeDataMapper;
	@Autowired
	ExcelData excelData;

	public Map<String, String> getCodeMap()
	{
		Map<String, String> outMap = new HashMap<>();
		List<FinanceVO> innerList = this.financeDataMapper.getAllCode();
		for (FinanceVO finVO : innerList)
			outMap.put(finVO.getFinName(), finVO.getFinCode());
		return outMap;
	}
	public List<String> getURLList()
	{
		return null;
	}
	public synchronized ResponseEntity<String> excelInsert()
	{
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
				return new ResponseEntity<String>("500", HttpStatus.OK);
			}
			// List<FinanceVO> innerList = this.excelData.callExcel(uploadFile);
			List<FinanceVO> innerList = this.excelData.callExcel();
			for (FinanceVO finVO : innerList)
				/* 종목을 하나씩 삽입한다. */
				this.financeDataMapper.insertSharesInfo(finVO);
			/* 성공하면 200 응답. */
			return new ResponseEntity<String>("200", HttpStatus.OK);
		}
		catch (Exception e)
		{
			/* 중간에러발생시 400 응답. */
			return new ResponseEntity<String>("400", HttpStatus.OK);
		}
	}
}
