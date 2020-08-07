package com.thoughtworks.todolist.service;

import com.thoughtworks.todollist.exception.ExceptionMessage;
import com.thoughtworks.todollist.exception.NotExistTodoException;
import com.thoughtworks.todollist.model.Todo;
import com.thoughtworks.todollist.repository.TodoRepository;
import com.thoughtworks.todollist.service.TodoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TodoServiceTest {
    private static List<Todo> todos = new ArrayList<>();
    @Mock
    TodoRepository todoRepository;
    @InjectMocks
    TodoService todoService;

    @BeforeAll
    static void init() {
        for (int j = 0; j < 10; j++) {
            todos.add(new Todo(j, "todo" + j, false));
        }
    }

    @Test
    void should_return_todo_when_add_todo_given_todo() {
        //given
        Todo todo = new Todo(1, "todo1", false);
        when(todoRepository.save(todo)).thenReturn(todo);
        //when
        Todo newTodo = todoService.addTodo(todo);
        //then
        assertNotNull(newTodo);
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    void should_return_todos_when_find_all_given_no_parameters() {
        //given

        when(todoRepository.findAll()).thenReturn(todos);
        //when
        List<Todo> actualTodos = todoService.getTodoList();
        //then
        assertEquals(todos.size(), actualTodos.size());
    }

    @Test
    void should_return_none_when_delete_todo_given_wrong_todo_id() {
        //given
        NotExistTodoException notExistTodoException = assertThrows(NotExistTodoException.class, () -> {
            todoService.deleteTodoById(anyInt());
        });
        //when
        //then
        assertEquals(ExceptionMessage.NOT_EXISTS_TODO.getValue(), notExistTodoException.getMessage());
    }

    @Test
    void should_return_updated_todo_when_update_todo_by_id_given_todo_id() throws NotExistTodoException {
        //given
        Todo todo = new Todo(1, "todo", false);
        Todo updatedTodo = new Todo(1, "todo1", true);
        when(todoRepository.findById(todo.getId())).thenReturn(Optional.of(todo));
        when(todoRepository.save(updatedTodo)).thenReturn(updatedTodo);
        //when
        Todo actualTodo = todoService.updateTodoById(updatedTodo.getId(), updatedTodo);
        //then
        assertNotNull(actualTodo);
    }

    @Test
    void should_return_none_when_update_todo_by_id_given_wrong_todo_id() {
        //given
        Todo todo = new Todo(1, "todo", false);
        Todo updateTodo = new Todo(1, "todo2", false);
        when(todoRepository.findById(1)).thenReturn(Optional.empty());
        //when
        NotExistTodoException notExistTodoException = assertThrows(NotExistTodoException.class, () -> {
            todoService.updateTodoById(1, updateTodo);
        });
        //then
        assertEquals(ExceptionMessage.NOT_EXISTS_TODO.getValue(), notExistTodoException.getMessage());
    }
}
