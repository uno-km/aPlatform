package com.aPlatform.controller.main.SO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aPlatform.controller.main.DAO.MaindataLoadDAO;

@Controller
@RequestMapping(value = "/dataload")
public class MaindataLoadSO
{
	@Autowired
	MaindataLoadDAO maindataLoadDAO;
	@RequestMapping(method = RequestMethod.POST , value="/navbar")
	public void serviceList()
	{
		System.out.println(maindataLoadDAO.loadNavbar());
	}

	
}
