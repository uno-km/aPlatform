package com.aPlatform.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aPlatform.controller.user.VO.UserinfoOutVO;
import com.aPlatform.controller.user.VO.UserinfoVO;

@Mapper
public interface UserActiveMapper
{
	public UserinfoOutVO getUserInfo(UserinfoVO userinfoVO);

	public void signUp(UserinfoVO userinfoVO);

	public List<UserinfoVO> loadAllUserInfo();

	public int checkDuplicationId(UserinfoVO userinfoVO);
}
