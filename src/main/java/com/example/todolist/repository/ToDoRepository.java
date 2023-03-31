package com.example.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todolist.model.entity.ToDo;
import com.example.todolist.model.entity.ToDoStatus;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    ToDo findByStatus(ToDoStatus status);

}
