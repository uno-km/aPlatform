package com.aPlatform.controller.main.SO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aPlatform.controller.main.DAO.MaindataLoadDAO;
import com.aPlatform.controller.main.VO.NavbarVO;

@Controller
@RequestMapping(value = "/dataload")
public class MaindataLoadSO
{
	@Autowired
	MaindataLoadDAO maindataLoadDAO;
	@RequestMapping(method = RequestMethod.GET, value = "/navbar")
	public List<NavbarVO> serviceList(@RequestParam Map<String, String> param)
	{

		System.out.println(maindataLoadDAO.loadNavbar());
		return maindataLoadDAO.loadNavbar(param);
	}
}
