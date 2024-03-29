package com.aPlatform.controller.main.SO;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class APITestSO
{
	@DeleteMapping(value = "/header")
	public String deleteHeader(@RequestHeader Map<String, Object> header)
	{
		return null;
	}
	@DeleteMapping(value = "/body")
	public String deleteBody(@RequestBody Map<String, String> param)
	{
		return null;
	}
	@DeleteMapping(value = "/param")
	public String deleteParam(@RequestParam Map<String, Object> param)
	{
		return null;
	}
	@DeleteMapping(value = "/delete/{path}")
	public String deletePath(@PathVariable(value = "path") String path)
	{
		return null;
	}
	
	// @PostMapping(value = "/file")
	// private String postFile(@RequestParam("file") final MultipartFile file)
	// {
	// return null;
	// }
	@PostMapping(value = "/file")
	private String postFile(@RequestParam final Map<String, String> param, @RequestParam("file") final MultipartFile file)
	{
		return null;
	}
	
	@PostMapping(value = "/filePart")
	private String postPartFile(
			@RequestParam final Map<String, String> param
			, @RequestPart final MultipartFile file)
	{
		/*대강 파일 업로드 성공은 200, 실패는 400 코드를 클라이언트로 보내겠습니다.*/
		if (file.getSize() > 0)
		{
			/* 파일을 업로드하는 방식은 생략하겠습니다. */
			return "200";
		}
		else
		{
			return "400";
		}
	}
	
	@PutMapping(value = "/file")
	private String putFile(@RequestPart final MultipartFile file) throws Exception
	{
		return null;
	}
	@PatchMapping(value = "/file")
	private String patchFile(@RequestPart final MultipartFile file) throws Exception
	{
		return null;
	}
	// @GetMapping(value = "fetch")
	// private String getFetch()
	// {
	// return null;
	// }
	// @GetMapping(value = "fetch")
	// private String getFetch(@RequestParam final Map<String, String> param)
	// {
	// return null;
	// }
	@GetMapping(value = "fetch")
	@ResponseBody
	private Map<String, String> getFetch(@RequestParam final Map<String, String> param)
	{
		return param;
	}
	// @GetMapping(value = "fetch")
	// private Map<String, String> getFetch(
	// @RequestParam final Map<String, String> param)
	// {
	// return param;
	// }
	@GetMapping(value = "fetch/{name}/{age}")
	private String getPathFetch(@PathVariable(value = "name") String name, @PathVariable(value = "age") String age)
	{
		return null;
	}
}
