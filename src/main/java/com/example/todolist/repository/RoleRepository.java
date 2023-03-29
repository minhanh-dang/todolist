package com.example.todolist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todolist.model.entity.RoleName;
import com.example.todolist.model.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

	Optional<Roles> findByRoleName(RoleName roleUser);

}
