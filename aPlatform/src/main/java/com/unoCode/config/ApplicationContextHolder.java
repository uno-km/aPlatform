package com.unoCode.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHolder implements ApplicationContextAware
{
	private static ApplicationContext applicationContext;
	public static ApplicationContext getContext()
	{
		return applicationContext;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		// TODO Auto-generated method stub
		ApplicationContextHolder.applicationContext = applicationContext;
	}
}