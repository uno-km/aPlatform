package com.aPlatform.utils;

import com.aPlatform.utils.service.FileUnoUtils;

import lombok.Data;
@Data
public abstract class Utils
{
	Utils()
	{
	}
	public static FileUnoUtils file = new FileUnoUtils();
}
