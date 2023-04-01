package com.example.todolist.repository;

import com.example.todolist.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todolist.model.entity.ToDo;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    Optional<List<ToDo>> findByUser(User user);

    Optional<List<ToDo>> findByUserId(Long id);

}
