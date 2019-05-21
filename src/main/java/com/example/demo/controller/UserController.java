// package com.example.demo.controller;
//
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// @Controller
// @RequestMapping("/user")
// @Template(3)
// public class UserController {
//
// @Template(1)
// @RequestMapping(value = "/detail", method = RequestMethod.GET)
// @ResponseBody
// public String detailForTemplateOne(@RequestParam("id") long id) {
//
// return "1";
// }
//
// @Template(2)
// @RequestMapping(value = "/detail", method = { RequestMethod.GET,
// RequestMethod.POST })
// @ResponseBody
// public String detailForTemplateTwo(@RequestParam("id") long id) {
// return "2";
// }
//
// }