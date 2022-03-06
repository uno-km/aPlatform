package com.aPlatform.controller.user.BOC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return loginBO.signinUser(UserinfoVO);
	}
	public boolean checkDuplId(UserinfoVO UserinfoVO)
	{
		return loginBO.checkDuplId(UserinfoVO);
	}
}
