package com.thoughtworks.todolist.controller;

import com.thoughtworks.todolist.dto.TodoDto;
import com.thoughtworks.todolist.exception.NotExistTodoException;
import com.thoughtworks.todolist.mapper.TodoMapper;
import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class TodoController {
    @Autowired
    TodoMapper todoMapper;
    @Resource
    private TodoService todoService;

    @GetMapping
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    @PutMapping("/{id}")
    public TodoDto updateTodo(@PathVariable(name = "id") Integer id, @RequestBody TodoDto todoDto) throws NotExistTodoException {
        return todoService.updateTodoById(id, todoDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDto addTodo(@RequestBody TodoDto todoDto) {
        //Todo todo = todoMapper.toTodoEntity(todoDto);
        return todoService.addTodo(todoDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable(name = "id") Integer id) throws NotExistTodoException {
        todoService.deleteTodoById(id);
    }
}
