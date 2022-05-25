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
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
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
