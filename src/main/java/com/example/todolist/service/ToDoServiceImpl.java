package com.example.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.entity.ToDo;
import com.example.todolist.model.entity.ToDoStatus;
import com.example.todolist.model.entity.User;
import com.example.todolist.model.exception.BadRequestException;
import com.example.todolist.model.mapper.ToDoMapper;
import com.example.todolist.model.mapper.UserMapper;
import com.example.todolist.repository.ToDoRepository;
import com.example.todolist.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

	private final ToDoRepository toDoRepository;

	private final UserRepository userRepository;

	private final ToDoMapper toDoMapper;

	private final UserMapper userMapper;

	@Override
	public ToDoDTO getToDoById(Long id) {
		ToDo toDo = toDoRepository.findById(id).get();

		ToDoDTO toDoDTO = toDoMapper.toDTO(toDo);

		return toDoDTO;
	}

	// get a ToDoDTO, initialize a new ToDo with name, status; save it in repo and
	// the convert it to DTO again.
	@Override
	public ToDoDTO createToDo(ToDoDTO toDoDTO) {
		ToDo toDo = new ToDo();
		toDo.setName(toDoDTO.getName());
//		ToDoStatus status = toDoRepository.findByStatus(ToDoStatus.TO_DO).getStatus();
//		toDo.setStatus(status);
		toDo.setStatus(toDoDTO.getStatus());
		return toDoMapper.toDTO(toDoRepository.save(toDo));
	}

	@Override
	@Transactional
	public ToDoDTO createToDo(Long id, ToDoDTO toDoDTO) {

		User user = userRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found!"));

		ToDo toDo = new ToDo();
		toDo.setName(toDoDTO.getName());
		toDo.setUser(user);
		toDo.setStatus(toDoDTO.getStatus());
//		toDo.setStatus(ToDoStatus.TO_DO);
		ToDo savedToDo = toDoRepository.save(toDo);

		return toDoMapper.toDTO(savedToDo);

	}

	/////// GET method //////////

	@Override
	public List<ToDoDTO> getAllToDos() {
		List<ToDoDTO> toDoDTOS = toDoRepository.findAll().stream().map(todo -> ToDoMapper.getInstance().toDTO(todo))
				.collect(Collectors.toList());
//		List<ToDoResponse> toDoResponses = toDoDTOS.stream().map(todo -> ToDoMapper.getInstance().toResponse(todo))
//				.collect(Collectors.toList());
		return toDoDTOS;
	}

	@Override
	public List<ToDoDTO> getUserToDo(Long id) {
		List<ToDo> todo = toDoRepository.findByUserId(id).orElseThrow(()-> new BadRequestException("ToDo not found!"));
		List<ToDoDTO> result = todo.stream().map(t -> toDoMapper.toDTO(t))
				.collect(Collectors.toList());
		return result;
	}

//    @Override
//    public ToDoDTO getToDoById(int id) {
//        ToDo toDo = toDoRepository.findById(id).get();
//        return ToDoMapper.getInstance().toDTO(toDo);
//    }
//
//    @Override
//    public ToDoDTO getToDoByName(String name) {
//        ToDo toDo = toDoRepository.findByName(name);
//        return ToDoMapper.getInstance().toDTO(toDo);
//    }

	@Override
	public ToDoDTO updateToDo(Long id, ToDoDTO toDo) {
		ToDo existingToDo = toDoRepository.findById(id).orElseThrow(() -> new BadRequestException("ToDo not found!"));;
		existingToDo.setName(toDo.getName());
		existingToDo.setStatus(toDo.getStatus());
		ToDo updatedToDo = toDoRepository.save(existingToDo);
		return ToDoMapper.getInstance().toDTO(updatedToDo);
	}

	@Override
	public String deleteToDo(Long id) {
		toDoRepository.deleteById(id);
//		return "ToDo removed: " + id;
		return null;
	}
}