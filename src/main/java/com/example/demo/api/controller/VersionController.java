package com.example.demo.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/version/{version}")
public class VersionController {

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	@Version(1)
	public String detailForTemplateOne(@RequestParam("id") long id) {

		return "@Version(1)1";
	}

	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@Version(2)
	public String detailForTemplateTwo(@RequestParam("id") long id) {
		return "@Version(1)2";
	}

}