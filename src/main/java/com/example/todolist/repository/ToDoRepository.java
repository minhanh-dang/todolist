package com.example.todolist.repository;

import com.example.todolist.model.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    ToDo findByID(long id);
}
