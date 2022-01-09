package com.aPlatform.controller.main.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.main.BO.LoadNavbarBO;
import com.aPlatform.controller.main.VO.NavbarVO;
@Service
public class MaindataLoadDAO
{
	@Autowired
	LoadNavbarBO loadNavbarBO;
	public List<NavbarVO> loadNavbar(Map<String, String> param)
	{
		return loadNavbarBO.loadNavbar(param);
	}
}
