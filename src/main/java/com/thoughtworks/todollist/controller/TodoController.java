package com.thoughtworks.todollist.controller;

import com.thoughtworks.todollist.dto.TodoDto;
import com.thoughtworks.todollist.exception.NotExistTodoException;
import com.thoughtworks.todollist.model.Todo;
import com.thoughtworks.todollist.service.TodoService;
import lombok.AllArgsConstructor;
import mapper.TodoMapper;
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
//    @Autowired
//    private TodoMapper todoMapper;
    @Resource
    private TodoService todoService;

    @GetMapping
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable(name = "id") Integer id, @RequestBody Todo todo) throws NotExistTodoException {
        return todoService.updateTodoById(id, todo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Todo addTodo(@RequestBody TodoDto todoDto) {
//        Todo todo = TodoMapper.INSTANCE.toTodoEntity(todoDto);
//        return todoService.addTodo(todo);
//    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable(name = "id") Integer id) throws NotExistTodoException {
        todoService.deleteTodoById(id);
    }
}
