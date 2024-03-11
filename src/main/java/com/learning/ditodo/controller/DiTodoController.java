package com.learning.ditodo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/di-todo")
public class DiTodoController {

	@GetMapping("/hello")
	public static String hello() {
		return  "hello di-todo API";
	}
}
