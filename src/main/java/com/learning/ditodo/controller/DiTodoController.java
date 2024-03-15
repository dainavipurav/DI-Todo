package com.learning.ditodo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.ditodo.bean.DiTodo;

@RestController
@RequestMapping("/di-todo")
public class DiTodoController {

	public static ArrayList<DiTodo> diTodos = new ArrayList<>(
			Arrays.asList(new DiTodo(1, "Test1", "Description", true, null, null, "H"),
					new DiTodo(2, "Test2", "Description", true, null, null, "H"),
					new DiTodo(3, "Test3", "Description", true, null, null, "H"),
					new DiTodo(4, "Test4", "Description", true, null, null, "H"),
					new DiTodo(5, "Test5", "Description", true, null, null, "H")));

	@GetMapping("/hello")
	public static String hello() {
		return "hello di-todo API";
	}

	@GetMapping("/todos")
	public static Map<String, Object> getAllDiTodos() {
		Map<String, Object> responseMap = new HashMap<>();

		Map<String, Object> dataListMap = new HashMap<>();

		dataListMap.put("dataList", diTodos);

		responseMap.put("status", 200);
		responseMap.put("data", dataListMap);
		responseMap.put("message", "Successfully Completed");

		return responseMap;
	}

	@GetMapping("/todos/{id}")
	public static Map<String, Object> getTodoById(@PathVariable int id) {
		Map<String, Object> responseMap = new HashMap<>();

		DiTodo diTodo = diTodos.stream().filter(t -> t.getId() == id).findFirst().get();

		responseMap.put("status", 200);
		responseMap.put("data", diTodo);
		responseMap.put("message", "Successfully Completed");

		return responseMap;
	}

	@PostMapping("/todos")
	public static Map<String, Object> addDiTodo(@RequestBody DiTodo diTodo) {
		Map<String, Object> responseMap = new HashMap<>();

		try {
			diTodos.add(diTodo);
			responseMap.put("status", 200);
			responseMap.put("message", "di-todo added successfully");
		} catch (Exception e) {
			responseMap.put("status", "500");
			responseMap.put("message", "Error occured while adding di-todo due to" + e.getMessage());
		}

		return responseMap;
	}

	@PutMapping("/todos/{id}")
	public static Map<String, Object> updateDiTodoById(@PathVariable int id, @RequestBody DiTodo diTodo) {
		Map<String, Object> responseMap = new HashMap<>();

		try {
			int index = diTodos.indexOf(diTodos.stream().filter(t -> t.getId() == id).findFirst().get());

			diTodos.set(index, diTodo);
			responseMap.put("status", 200);
			responseMap.put("message", "di-todo updated successfully at id " + id);
		} catch (Exception e) {
			responseMap.put("status", "500");
			responseMap.put("message", "Error occured while adding di-todo due to" + e.getMessage());
		}

		return responseMap;
	}

}