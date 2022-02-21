package com.aPlatform.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aPlatform.controller.service.finance.VO.FinanceVO;

@Mapper
public interface FinanceDataMapper
{
	public List<FinanceVO> getCode();
}
