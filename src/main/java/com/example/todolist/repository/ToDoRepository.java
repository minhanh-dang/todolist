package com.example.todolist.repository;

import com.example.todolist.model.entity.ToDo;
import com.example.todolist.model.entity.ToDoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    ToDo findById(int id);
    ToDo findByStatus(ToDoStatus status);
}
