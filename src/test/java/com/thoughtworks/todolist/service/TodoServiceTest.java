package com.thoughtworks.todolist.service;

import com.thoughtworks.todolist.dto.TodoDto;
import com.thoughtworks.todolist.exception.ExceptionMessage;
import com.thoughtworks.todolist.exception.NotExistTodoException;
import com.thoughtworks.todolist.mapper.TodoMapper;
import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.repository.TodoRepository;
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
    TodoMapper todoMapper;
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
        TodoDto todoDto = new TodoDto(1, "todo1", false);
        Todo todo = new Todo(1, "todo1", false);

        when(todoMapper.toTodoEntity(todoDto)).thenReturn(todo);
        when(todoRepository.save(todo)).thenReturn(todo);
        when(todoMapper.toTodoDto(todo)).thenReturn(new TodoDto());
        //when
        TodoDto newTodoDto = todoService.addTodo(todoDto);
        //then
        assertNotNull(newTodoDto);
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
        TodoDto updatedTodoDto = new TodoDto(1, "todo2", false);
        Todo updatedTodo = new Todo(1, "todo2", false);
        when(todoMapper.toTodoEntity(updatedTodoDto)).thenReturn(updatedTodo);
        when(todoRepository.findById(updatedTodo.getId())).thenReturn(Optional.of(updatedTodo));
        when(todoRepository.save(updatedTodo)).thenReturn(updatedTodo);
        when(todoMapper.toTodoDto(updatedTodo)).thenReturn(updatedTodoDto);
        //when
        TodoDto actualTodoDto = todoService.updateTodoById(updatedTodoDto.getId(), updatedTodoDto);
        //then
        assertNotNull(actualTodoDto);
    }

    @Test
    void should_return_none_when_update_todo_by_id_given_wrong_todo_id() {
        //given
        TodoDto updatedTodoDto = new TodoDto(1, "todo2", false);
        Todo updatedTodo = new Todo(1, "todo2", false);
        when(todoRepository.findById(1)).thenReturn(Optional.empty());
        when(todoMapper.toTodoEntity(updatedTodoDto)).thenReturn(updatedTodo);
        //when
        NotExistTodoException notExistTodoException = assertThrows(NotExistTodoException.class, () -> {
            todoService.updateTodoById(updatedTodoDto.getId(), updatedTodoDto);
        });
        //then
        assertEquals(ExceptionMessage.NOT_EXISTS_TODO.getValue(), notExistTodoException.getMessage());
    }
}
