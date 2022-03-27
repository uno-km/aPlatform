package com.aPlatform.controller.service.cafe.SO;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.service.cafe.BO.CafeRetvBOC;

@RestController
@RequestMapping(value = "/service/cafe")
public class CafeRetvSO
{
	@Autowired
	CafeRetvBOC cafeRetvBOC;

	@GetMapping(value = "/main")
	private ModelAndView reternMainPage(Model model)
	{
		return cafeRetvBOC.reternMainPage();
	}
	@GetMapping(value = "/get")
	private Map<String, Object> get(String data)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(data, data);
		return map;
	}
	@GetMapping(value = "/getData")
	private Map<String, Object> getData(@RequestParam Map<String, Object> innerMap)
	{
		return innerMap;
	}
	@ResponseBody
	@PostMapping(value = "/post")
	private Map<String, Object> post(@RequestBody Map<String, Object> innerMap)
	{
		return innerMap;
	}
	@ResponseBody
	@PostMapping(value = "/postData")
	private Map<String, Object> postData(@RequestBody Map<String, Object> innerMap)
	{
		return innerMap;
	}
	@ResponseBody
	@PutMapping(value = "/put")
	private Map<String, Object> put(@RequestBody Map<String, Object> innerMap)
	{
		return innerMap;
	}
	@ResponseBody
	@DeleteMapping(value = "/delete")
	private Map<String, Object> delete(@RequestBody Map<String, Object> innerMap)
	{
		return innerMap;
	}

	@GetMapping("/getAgeByName")
	/* inputName 파라미터를 받아, 미리 저장된 ageMap에서 해당 이름에 맵핑된 나이를 리턴해주는 메소드 */
	public Map<String, Object> getAgeByName(@RequestParam String inputName)
	{
		Map<String, Integer> ageMap = new HashMap<>();
		ageMap.put("tom", 10);
		ageMap.put("bob", 20);
		ageMap.put("kim", 30);

		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("name", inputName);
		returnMap.put("age", ageMap.get(inputName));

		return returnMap;
	}
	@PostMapping("/postAgeByName")
	/* inputMap 파라미터를 받아, 미리 저장된 ageMap에서 해당 이름에 맵핑된 나이를 리턴해주는 메소드 */
	public Map<String, Object> postAgeByName(@RequestBody Map<String, Object> inputMap)
	{
		Map<String, Integer> ageMap = new HashMap<>();
		ageMap.put("tom", 10);
		ageMap.put("bob", 20);
		ageMap.put("kim", 30);

		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("name", inputMap.get("name"));
		returnMap.put("age", ageMap.get(inputMap.get("name")));

		return returnMap;
	}
}
