package com.aPlatform.controller.main.BO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.main.VO.MainDataLoadOutVO;
import com.aPlatform.controller.main.VO.NavbarVO;
import com.aPlatform.mappers.LoaddataMapper;

@Service
public class LoadNavbarBO
{
	@Autowired
	LoaddataMapper loaddataMapper;
	public MainDataLoadOutVO loadNavbar(Map<String, String> param)
	{
		MainDataLoadOutVO outVO = new MainDataLoadOutVO();
		NavbarVO navbarVO = new NavbarVO();
		navbarVO.setUser_id(param.get("user_id"));
		outVO.setNavbaroutVO(loaddataMapper.loadNavbar(navbarVO));
		return outVO;
	}
}
