package com.learning.ditodo.repository;

import org.springframework.data.repository.CrudRepository;

import com.learning.ditodo.bean.DiTodo;

public interface DiTodoRepository extends CrudRepository<DiTodo, Integer> {

}
