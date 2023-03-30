package com.example.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.entity.ToDo;
import com.example.todolist.model.entity.ToDoStatus;
import com.example.todolist.model.entity.User;
import com.example.todolist.model.mapper.ToDoMapper;
import com.example.todolist.repository.ToDoRepository;
import com.example.todolist.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

	private final ToDoRepository toDoRepository;

	private final UserRepository userRepository;

	private final ToDoMapper toDoMapper;

	@Override
	public ToDoDTO createToDo(ToDoDTO toDoDTO) {
		ToDo toDo = new ToDo();
		toDo.setName(toDoDTO.getName());
//		toDo.setUserId(toDoDTO.getUserId());
		ToDoStatus status = toDoRepository.findByStatus(ToDoStatus.TO_DO).getStatus();
		toDo.setStatus(status);
		return toDoMapper.toDTO(toDoRepository.save(toDo));
	}

//	@Override
//	public List<ToDoDTO> createToDos(List<ToDoDTO> toDoDTOS) {
//		List<ToDo> toDos = toDoDTOS.stream().map(toDo -> ToDoMapper.getInstance().toEntity(toDo))
//				.collect(Collectors.toList());
//		List<ToDoDTO> toDoDTOList = toDos.stream()
//				.map(toDo -> ToDoMapper.getInstance().toDTO(toDoRepository.save(toDo))).collect(Collectors.toList());
//		return toDoDTOList;
//	}
	/////// GET method //////////

	@Override
	public List<ToDoDTO> getAllToDos() {
		return toDoRepository.findAll().stream().map(toDo -> ToDoMapper.getInstance().toDTO(toDo))
				.collect(Collectors.toList());
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
	public ToDoDTO updateToDo(ToDoDTO toDo) {
		ToDo existingToDo = toDoRepository.findById(toDo.getId()).get();
		existingToDo.setName(toDo.getName());
		existingToDo.setStatus(toDo.getStatus());
		ToDo updatedToDo = toDoRepository.save(existingToDo);
		return ToDoMapper.getInstance().toDTO(updatedToDo);
	}

	@Override
	public String deleteToDo(Long id) {
		toDoRepository.deleteById(id);
		return "ToDo removed: " + id;
	}

	@Override
	public ToDoDTO createToDo(Long id, ToDoDTO toDoDTO) {

		User user = userRepository.findById(id).get();
		ToDo toDo = new ToDo();
		toDo.setName(toDoDTO.getName());
		toDo.setUser(user);
		toDo.setStatus(ToDoStatus.TO_DO);
		ToDo savedToDo = toDoRepository.save(toDo);

		return toDoMapper.toDTO(savedToDo);
	}
}