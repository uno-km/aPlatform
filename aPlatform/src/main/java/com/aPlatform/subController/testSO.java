package com.aPlatform.subController;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class testSO
{
	// @PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// public void uploadFormPost(MultipartFile[] uploadFile, Model model) throws Exception
	// {
	// String uploadFolder = "C:\\programing\\upload";
	// for (MultipartFile multipartFile : uploadFile)
	// {
	// File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
	// multipartFile.transferTo(saveFile);
	// } // end for
	// }
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) throws Exception
	{
		List<AttachFileDTO> list = new ArrayList<>();
		String uploadFolder = "C:\\programing\\upload";
		String uploadFolderPath = getFolder();
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		if(uploadPath.exists() == false)
		{
			uploadPath.mkdirs();
		}
		for (MultipartFile multipartFile : uploadFile)
		{
			AttachFileDTO attachDTO = new AttachFileDTO();
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			attachDTO.setFileName(uploadFileName);
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			File saveFile = new File(uploadPath, uploadFileName);
			multipartFile.transferTo(saveFile);
			attachDTO.setUuid(uuid.toString());
			attachDTO.setUploadPath(uploadFolderPath);
			if(checkImageType(saveFile))
			{
				attachDTO.setImage(true);
			}
			attachDTO.setImage(false);
			list.add(attachDTO);
		} // end for
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	private boolean checkImageType(File file) throws Exception
	{
		if(null == Files.probeContentType(file.toPath()))
		{
			return false;
		}
		String contentType = Files.probeContentType(file.toPath());
		return contentType.startsWith("image");
	}
	private String getFolder()
	{
		SimpleDateFormat t = new SimpleDateFormat("yyyy-mm-dd");
		Date date = new Date();
		String str = t.format(date);
		return str.replace("-", File.separator);
	}
}
