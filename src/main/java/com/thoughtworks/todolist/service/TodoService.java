package com.thoughtworks.todolist.service;

import com.thoughtworks.todolist.dto.TodoDto;
import com.thoughtworks.todolist.exception.ExceptionMessage;
import com.thoughtworks.todolist.exception.NotExistTodoException;
import com.thoughtworks.todolist.mapper.TodoMapper;
import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = todoMapper.toTodoEntity(todoDto);
        Todo newTodo = todoRepository.save(todo);
        return todoMapper.toTodoDto(newTodo);
    }

    public List<Todo> getTodoList() {
        return todoRepository.findAll();
    }

    public TodoDto updateTodoById(Integer id, TodoDto updatedTodoDto) throws NotExistTodoException {
        if (!id.equals(updatedTodoDto.getId())) {
            throw new NotExistTodoException(ExceptionMessage.NOT_EXISTS_TODO);
        }
        Todo updatedTodo = todoMapper.toTodoEntity(updatedTodoDto);
        todoRepository.findById(updatedTodo.getId())
                .orElseThrow(() -> new NotExistTodoException(ExceptionMessage.NOT_EXISTS_TODO));
        Todo newTodo = todoRepository.save(updatedTodo);
        return todoMapper.toTodoDto(newTodo);
    }

    public void deleteTodoById(Integer id) throws NotExistTodoException {
        todoRepository.findById(id)
                .orElseThrow(() -> new NotExistTodoException(ExceptionMessage.NOT_EXISTS_TODO));
        todoRepository.deleteById(id);
    }
}
