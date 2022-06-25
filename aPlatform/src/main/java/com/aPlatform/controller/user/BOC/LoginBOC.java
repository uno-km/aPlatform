package com.aPlatform.controller.user.BOC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.common.model.CommonOutVO;
import com.aPlatform.controller.common.model.ResultDTO;
import com.aPlatform.controller.user.BO.LoginBO;
import com.aPlatform.controller.user.VO.UserinfoVO;
import com.unoCode.Validation;

@Service
public class LoginBOC
{

	@Autowired
	LoginBO loginBO;

	public CommonOutVO registerUser(UserinfoVO UserinfoVO)
	{
		CommonOutVO commonoutVO = new CommonOutVO();
		ResultDTO result = new ResultDTO();
		commonoutVO.setResultDTO(result);
		/* id에 특수문자가 들어오면 false를 반납해버림 */
		if(!Validation.isSymbolContainCheck(UserinfoVO.getUser_id()))
		{
			result.setCode("500");
			result.setMessage("특수문자 및 사용할 수 없는 문자가 들어왔습니다. 다시 한번 확인해 주세요.");
			return commonoutVO;
		}
		else
		{
			return loginBO.registerUser(UserinfoVO);
		}
	}

	public CommonOutVO signinUser(UserinfoVO UserinfoVO)
	{
		return loginBO.signinUser(UserinfoVO);
	}
	public CommonOutVO checkDuplId(UserinfoVO UserinfoVO)
	{
		return loginBO.checkDuplId(UserinfoVO);
	}
}
