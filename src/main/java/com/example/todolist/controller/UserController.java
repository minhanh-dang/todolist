package com.example.todolist.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.mapper.ToDoMapper;
import com.example.todolist.model.mapper.UserMapper;
import com.example.todolist.model.request.ToDoRequest;
import com.example.todolist.model.response.ToDoResponse;
import com.example.todolist.model.response.UserInfoResponse;
import com.example.todolist.security.CurrentUser;
import com.example.todolist.security.UserPrincipal;
import com.example.todolist.service.ToDoService;
import com.example.todolist.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	private final ToDoService toDoService;

	private final UserMapper userMapper;

	private final ToDoMapper todoMapper;

	@GetMapping("/me")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public UserInfoResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserDto userDto = userService.getUserById(currentUser.getId());

		return userMapper.toResponse(userDto);
	}

	@PostMapping("/addToDo")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ToDoResponse createToDo(@RequestBody ToDoRequest request, @CurrentUser UserPrincipal currentUser) {
		ToDoDTO toDoDTO = ToDoMapper.getInstance().toDto(request);
		System.out.println(toDoDTO.getName());
		ToDoDTO savedToDoDTO = toDoService.createToDo(currentUser.getId(), toDoDTO);
		return todoMapper.toResponse(savedToDoDTO);
	}

	@GetMapping("/todos")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<ToDoResponse> getAllToDos() {
		List<ToDoDTO> toDoDTO = toDoService.getAllToDos();
		return toDoDTO.stream().map(todo -> ToDoMapper.getInstance().toResponse(todo)).collect(Collectors.toList());
	}

	@GetMapping("/me/todos")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public List<ToDoResponse> getUserToDo(@CurrentUser UserPrincipal currentUser) {
		List<ToDoDTO> toDoDTO = toDoService.getUserToDo(currentUser.getId());
		return toDoDTO.stream().map(todo -> ToDoMapper.getInstance().toResponse(todo)).collect(Collectors.toList());
	}

	@PutMapping("/me/updateToDo/{todo_id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ToDoResponse updateToDo(@CurrentUser UserPrincipal currentUser, @PathVariable Long todo_id,
			@RequestBody ToDoRequest toDoRequest) {
		ToDoDTO updatedToDo = toDoService.updateToDo(todo_id, todoMapper.toDto(toDoRequest));
		return todoMapper.toResponse(updatedToDo);
	}

	@DeleteMapping("/me/deleteToDo/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> deleteToDo(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
		toDoService.deleteToDo(id);
		return new ResponseEntity<>("ToDo successfully deleted!", HttpStatus.OK);
	}

}