package com.aPlatform.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.web.multipart.MultipartFile;

public class FileUnoUtils
{

	public static File multipartFileToFile(MultipartFile file) throws IOException
	{
		/* 실제 업로드된 파일 이름을 상수형으로 저장 */
		final String MULTIFILE_REAL_NAME = file.getOriginalFilename();
		/* 아무이름이로 상수형으로 저장 - 업로드하는 파일의 같은 경로에 실제 존재하긴하는 파일이름 */
		final String FILE_REAL_NAME = "data_2032_20221026.xlsx";
		/** 
		 * 실제업로드된 파일이름과 하드코딩한 파일이름 비교
		 * @True	: 파일명이 같다.
		 * @False	: 파일명이 다르다.
		 */
		if(MULTIFILE_REAL_NAME.equals(FILE_REAL_NAME))
		{
			System.out.println("");
		}
		/* 하드코딩된 파일이름으로 파일하나 생성(아무것도 안들어있다.) */
		File convFile = new File(FILE_REAL_NAME);
		/** 
		 * @author	: 인터넷에 떠도는 소스
		 * @Desc  	: 파일을 새로 생성하는듯하다.
		 */ 
		convFile.createNewFile();
		/** 
		 * @author	: 인터넷에 떠도는 소스
		 * @Desc  	: 우선 파일아웃스트림으로 작성되어질 파일을 객체로 생성하는것 같다.
		 */ 
		FileOutputStream fos = new FileOutputStream(convFile);
		/** 
		 * @author	: 인터넷에 떠도는 소스
		 * @Desc  	: fos에 file(클라이언트로 부터 받아온 MultipartFile file 매개변수)의 바이트정보를 주입
		 */ 
		fos.write(file.getBytes());
		fos.close();
		/** 
		 * @author	: uno-kim
		 * @Desc  	: 확인절차
		 */ 
		boolean isFile = convFile.isFile();
		boolean canRead = convFile.canRead();
		boolean canWrite = convFile.canWrite();
		boolean exists = convFile.exists();
		long length = convFile.length();
		String contentType = Files.probeContentType(convFile.toPath());
		return convFile;
	}
	public static boolean checkImageType(File file)
	{
		try
		{
			return Files.probeContentType(file.toPath()).startsWith("image");
		}
		catch (IOException e)
		{
			return false;
		}
	}
}
