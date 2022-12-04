package com.aPlatform.utils.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.web.multipart.MultipartFile;
public class FileUnoUtils
{
	public File multipartFileToFile(MultipartFile file) throws IOException
	{
		/* 업로드하는 파일의 같은 경로에 실제 존재하긴하는 파일이름 */
		File convFile = new File(file.getOriginalFilename());
		/**
		 * @Desc : 파일을 새로 생성하는듯하다.
		 */
		convFile.createNewFile();
		/**
		 * @Desc : 우선 파일아웃스트림으로 작성되어질 파일을 객체로 생성하는것 같다.
		 */
		FileOutputStream fos = new FileOutputStream(convFile);
		/**
		 * @Desc : fos에 file(클라이언트로 부터 받아온 MultipartFile file 매개변수)의 바이트정보를 주입
		 */
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
	public boolean checkImageType(File file)
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
