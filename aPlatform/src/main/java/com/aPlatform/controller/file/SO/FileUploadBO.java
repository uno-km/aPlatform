package com.aPlatform.controller.file.SO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aPlatform.controller.file.VO.FileInVO;

@Service
public class FileUploadBO
{
	public ResponseEntity<String> uploadExcel(final MultipartFile file)
	{
		String uploadFolder = "C:\\programing\\upload\\fin\\excel";
		File uploadPath = new File(uploadFolder);
		if(uploadPath.exists() == false) uploadPath.mkdirs();
		FileInVO attachDTO = new FileInVO();
		String uploadFileName = file.getOriginalFilename();
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
		UUID uuid = UUID.randomUUID();
		uploadFileName = uuid.toString() + "_" + uploadFileName;
		try
		{
			File saveFile = new File(uploadPath, uploadFileName);
			file.transferTo(saveFile);
			attachDTO.setFileRealNm(uploadFileName);
			attachDTO.setFileChangedNm(uuid.toString());
			attachDTO.setSavePath(uploadFolder);
			if(checkImageType(saveFile))
				attachDTO.setImgYn("Y");
			else
				attachDTO.setImgYn("N");
			return new ResponseEntity<>("200", HttpStatus.OK);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<>("400", HttpStatus.OK);
		}
	}
	private boolean checkImageType(File file)
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
