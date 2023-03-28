package com.example.todolist.repository;

import com.example.todolist.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
<<<<<<< HEAD:src/main/java/com/example/todolist/repository/UserInfoRepository.java
public interface UserInfoRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);

    Optional<User> findById(int id);


=======
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserName(String userName);
>>>>>>> 37c92f4d0fa3a28715cb33e3820124eab1a6fa72:src/main/java/com/example/todolist/repository/UserRepository.java
}
