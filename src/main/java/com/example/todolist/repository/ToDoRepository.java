package com.example.todolist.repository;

import com.example.todolist.model.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    ToDo findById(int id);
}
