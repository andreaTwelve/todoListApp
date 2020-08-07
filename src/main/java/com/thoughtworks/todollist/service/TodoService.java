package com.thoughtworks.todollist.service;

import com.thoughtworks.todollist.exception.ExceptionMessage;
import com.thoughtworks.todollist.exception.NotExistTodoException;
import com.thoughtworks.todollist.model.Todo;
import com.thoughtworks.todollist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> getTodoList() {
        return todoRepository.findAll();
    }

    public Todo updateTodoById(Integer id, Todo todo) throws NotExistTodoException {
        if (!id.equals(todo.getId())) {
            throw new NotExistTodoException(ExceptionMessage.NOT_EXISTS_TODO);
        }
        todoRepository.findById(todo.getId())
                .orElseThrow(() -> new NotExistTodoException(ExceptionMessage.NOT_EXISTS_TODO));
        return todoRepository.save(todo);
    }

    public void deleteTodoById(Integer id) throws NotExistTodoException {
        todoRepository.findById(id)
                .orElseThrow(() -> new NotExistTodoException(ExceptionMessage.NOT_EXISTS_TODO));
        todoRepository.deleteById(id);
    }
}
