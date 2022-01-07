package com.aPlatform.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.aPlatform.controller.user.VO.UserinfoVO;

@Mapper
public interface userActiveMapper
{
	public UserinfoVO getUserInfo(UserinfoVO userVO);

	public void signUp(UserinfoVO userVO);

	public int checkDuplicationId(UserinfoVO userVO);
}
