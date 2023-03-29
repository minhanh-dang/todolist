package com.example.todolist.controller;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.mapper.UserMapper;
import com.example.todolist.model.response.UserInfoResponse;
import com.example.todolist.security.CurrentUser;
import com.example.todolist.security.UserPrincipal;
import com.example.todolist.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	private final ToDoService toDoService;

	private final UserMapper userMapper;

	@GetMapping("/me")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public UserInfoResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {

		UserDto userDto = userService.getUserById(currentUser.getId());

		return userMapper.toResponse(userDto);
	}

	@PostMapping("/addToDo")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
//	@PreAuthorize("#toDoDTO.userId == authentication.principal.username")
	public ResponseEntity<ToDoDTO> createToDo(@RequestBody ToDoDTO toDoDTO){
		ToDoDTO savedToDoDTO = toDoService.createToDo(toDoDTO);
		return new ResponseEntity<>(savedToDoDTO, HttpStatus.CREATED);
	}

//	@PostMapping("/addToDos")
//	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
//	public ResponseEntity<List<ToDoDTO>> createToDos(@RequestBody List<ToDoDTO> toDoDTOs){
//		List<ToDoDTO> savedToDoDTOs = toDoService.createToDos(toDoDTOs);
//		return new ResponseEntity<>(savedToDoDTOs, HttpStatus.CREATED);
//	}

	@GetMapping("/todos")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<ToDoDTO>> getAllToDos(){
		List<ToDoDTO> toDos = toDoService.getAllToDos();
		return new ResponseEntity<>(toDos,HttpStatus.OK);
	}

	@PutMapping("/updateToDo")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<ToDoDTO> updateToDo(@RequestBody ToDoDTO toDoDTO){
		ToDoDTO updatedToDo = toDoService.updateToDo(toDoDTO);
		return new ResponseEntity<>(updatedToDo, HttpStatus.OK);
	}

	@DeleteMapping ("/deleteProduct/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> deleteToDo(@PathVariable int id){
		toDoService.deleteToDo(id);
		return new ResponseEntity<>("ToDo successfully deleted!",HttpStatus.OK);
	}

}