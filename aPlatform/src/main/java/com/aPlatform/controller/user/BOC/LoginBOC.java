package com.aPlatform.controller.user.BOC;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.aPlatform.controller.user.BO.LoginBO;
import com.aPlatform.controller.user.VO.UserinfoOutVO;
import com.aPlatform.controller.user.VO.UserinfoVO;
import com.unoCode.Validation;

@Service
public class LoginBOC
{

	@Autowired
	LoginBO loginBO;

	public boolean registerUser(UserinfoVO UserinfoVO)
	{
		/* id에 특수문자가 들어오면 false를 반납해버림 */
		if(!Validation.isSymbolContainCheck(UserinfoVO.getUser_id()))
		{
			return false;
		}
		return loginBO.registerUser(UserinfoVO);
	}

	public UserinfoOutVO signinUser(UserinfoVO UserinfoVO)
	{
		if(loginBO.checkUser(UserinfoVO))
		{
			return loginBO.signinUser(UserinfoVO);
		}
		return null;
	}
	public boolean checkDuplId(UserinfoVO UserinfoVO)
	{
		return loginBO.checkDuplId(UserinfoVO);
	}

	public void setSession(UserinfoOutVO outVO, HttpSession session,
			Model model)
	{
		session.setAttribute("userAuth", outVO.getUser_auth());
		session.setAttribute("userBirt", outVO.getUser_birth());
		session.setAttribute("userEmail", outVO.getUser_email());
		session.setAttribute("userId", outVO.getUser_id());
		session.setAttribute("userName", outVO.getUser_name());
		session.setAttribute("userPassword", outVO.getUser_password());
		session.setAttribute("userPhoneNum", outVO.getUser_phonenum());
		model.addAttribute("userAuth", outVO.getUser_auth());
		model.addAttribute("userBirth", outVO.getUser_birth());
		model.addAttribute("userEmail", outVO.getUser_email());
		model.addAttribute("userId", outVO.getUser_id());
		model.addAttribute("userName", outVO.getUser_name());
		model.addAttribute("userPassword", outVO.getUser_password());
		model.addAttribute("userPhoneNum", outVO.getUser_phonenum());
	}
}
