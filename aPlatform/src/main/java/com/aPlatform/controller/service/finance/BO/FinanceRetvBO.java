package com.aPlatform.controller.service.finance.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.VO.FinanceVO;
import com.aPlatform.mappers.FinanceDataMapper;

@Service
public class FinanceRetvBO
{
	@Autowired
	FinanceDataMapper financeDataMapper;
	public List<FinanceVO> getCode()
	{
		return financeDataMapper.getCode();
	}
}
