package com.learning.ditodo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.ditodo.bean.DiTodo;
import com.learning.ditodo.repository.DiTodoRepository;

@RestController
@RequestMapping("/di-todo")
public class DiTodoController {

	@Autowired
	private DiTodoRepository repository;

//	public static ArrayList<DiTodo> diTodos = new ArrayList<>(
//			Arrays.asList(new DiTodo(1, "Test1", "Description", true, null, null, "H"),
//					new DiTodo(2, "Test2", "Description", true, null, null, "H"),
//					new DiTodo(3, "Test3", "Description", true, null, null, "H"),
//					new DiTodo(4, "Test4", "Description", true, null, null, "H"),
//					new DiTodo(5, "Test5", "Description", true, null, null, "H"))
//	);

	public List<DiTodo> diTodos = new ArrayList<>();

	@GetMapping("/hello")
	public String hello() {
		return "hello DI-Todo API";
	}

	@GetMapping("/todos")
	public Map<String, Object> getAllDiTodos() {
		Map<String, Object> responseMap = new HashMap<>();

		Map<String, Object> dataListMap = new HashMap<>();

		try {
			repository.findAll().forEach(diTodos::add);

			dataListMap.put("dataList", diTodos);

			responseMap.put("status", 200);
			responseMap.put("data", dataListMap);
			responseMap.put("message", "Successfully Completed");

		} catch (Exception e) {
			responseMap.put("status", 500);
			responseMap.put("message", "Error occured while fetching DI-Todos due to " + e.getMessage());
		}

		return responseMap;
	}

	@GetMapping("/todos/{id}")
	public Map<String, Object> getTodoById(@PathVariable int id) {
		Map<String, Object> responseMap = new HashMap<>();

		try {
			DiTodo diTodo = diTodos.stream().filter(t -> t.getId() == id).findFirst().get();

			responseMap.put("status", 200);
			responseMap.put("data", diTodo);
			responseMap.put("message", "Successfully Completed");
		} catch (Exception e) {
			responseMap.put("status", 500);
			responseMap.put("message", "Error occured while fetching DI-Todo due to " + e.getMessage());
		}

		return responseMap;
	}

	@PostMapping("/todos")
	public Map<String, Object> addDiTodo(@RequestBody DiTodo diTodo) {
		Map<String, Object> responseMap = new HashMap<>();

		try {
//			diTodos.add(diTodo);

			repository.save(diTodo);
			responseMap.put("status", 200);
			responseMap.put("message", "DI-Todo added successfully");
		} catch (Exception e) {
			responseMap.put("status", 500);
			responseMap.put("message", "Error occured while adding DI-Todo due to " + e.getMessage());
		}

		return responseMap;
	}

	@PutMapping("/todos/{id}")
	public Map<String, Object> updateDiTodoById(@PathVariable int id, @RequestBody DiTodo diTodo) {
		Map<String, Object> responseMap = new HashMap<>();

		try {
			int index = diTodos.indexOf(diTodos.stream().filter(t -> t.getId() == id).findFirst().get());

			diTodos.set(index, diTodo);
			responseMap.put("status", 200);
			responseMap.put("message", "DI-Todo updated successfully at id " + id);
		} catch (Exception e) {
			responseMap.put("status", 500);
			responseMap.put("message", "Error occured while updating DI-Todo due to " + e.getMessage());
		}

		return responseMap;
	}

	@DeleteMapping("/todos/{id}")
	public Map<String, Object> deleteDiTodoById(@PathVariable int id) {
		Map<String, Object> responseMap = new HashMap<>();

		try {
			int index = diTodos.indexOf(diTodos.stream().filter(t -> t.getId() == id).findFirst().get());

			diTodos.remove(index);

			responseMap.put("status", 200);
			responseMap.put("message", "DI-Todo with id " + id + " deleted successfully");
		} catch (Exception e) {
			responseMap.put("status", 500);
			responseMap.put("message", "Error occured while deleting DI-todo due to " + e.getMessage());
		}

		return responseMap;
	}

	@DeleteMapping("/todos")
	public Map<String, Object> deleteAllDiTodos() {
		Map<String, Object> responseMap = new HashMap<>();

		try {
			diTodos.clear();

			responseMap.put("status", 200);
			responseMap.put("message", "All DI-Todos deleted successfully");
		} catch (Exception e) {
			responseMap.put("status", 500);
			responseMap.put("message", "Error occured while deleting all DI-Todos due to " + e.getMessage());
		}

		return responseMap;
	}

}