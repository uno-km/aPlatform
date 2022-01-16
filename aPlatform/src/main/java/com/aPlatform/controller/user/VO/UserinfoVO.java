package com.aPlatform.controller.user.VO;

import java.util.List;

import lombok.Data;

@Datapublic class UserinfoVO
{
	private String user_id;
	private String user_password;
	private String user_email;
	private String user_name;
	private String user_phonenum;
	// private String user_auth;
	private List<UserinfoVO> user_auth;
	private String user_birth;
}
