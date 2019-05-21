package com.example.demo.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/other")
public class OtherController {

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public String detailForTemplateOne(long id) {

		return "11";
	}

}