package com.aPlatform.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.aPlatform.controller.user.VO.UserinfoVO;

@Mapper
public interface userActiveMapper
{
	public UserinfoVO getUserInfo(UserinfoVO userinfoVO);

	public void signUp(UserinfoVO userinfoVO);

	public int checkDuplicationId(UserinfoVO userinfoVO);
}
