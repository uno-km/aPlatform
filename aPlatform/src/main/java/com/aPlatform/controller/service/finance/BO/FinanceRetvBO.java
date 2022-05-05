package com.aPlatform.controller.service.finance.BO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.VO.FinanceVO;
import com.aPlatform.mappers.FinanceDataMapper;

@Service
public class FinanceRetvBO
{
	@Autowired
	FinanceDataMapper financeDataMapper;

	public Map<String, String> getCodeMap()
	{
		Map<String, String> outMap = new HashMap<>();
		List<FinanceVO> inList = financeDataMapper.getAllCode();
		for (FinanceVO finVO : inList)
			outMap.put(finVO.getFinName(), finVO.getFinCode());
		return outMap;
	}
	public List<String> getURLList()
	{
		return null;
	}
}
