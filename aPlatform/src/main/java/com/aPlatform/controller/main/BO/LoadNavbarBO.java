package com.aPlatform.controller.main.BO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.main.VO.NavbarVO;
import com.aPlatform.mappers.LoaddataMapper;

@Service
public class LoadNavbarBO
{
	@Autowired
	LoaddataMapper loaddataMapper;
	public List<NavbarVO> loadNavbar(Map<String, String> param)
	{
		String user_id = "qwe";
		NavbarVO navbarVO = new NavbarVO();
		navbarVO.setUser_id(user_id);
		List<NavbarVO> list = loaddataMapper.loadNavbar(navbarVO);
		return list;
	}
}
