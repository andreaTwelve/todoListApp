package com.thoughtworks.todolist.mapper;

import com.thoughtworks.todolist.dto.TodoDto;
import com.thoughtworks.todolist.model.Todo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
@SpringBootTest
public class TodoMapperTest {
    @Autowired
    TodoMapper todoMapper;
    @Test
    void should_return_todo_when_to_todo_entity_given_todoDto() {
        //given
        TodoDto todoDto = new TodoDto(1, "todo1", false);
        //when
        Todo todo = todoMapper.toTodoEntity(todoDto);
        //then
        assertEquals(todo.getId(), todoDto.getId());
        assertEquals(todo.getContent(), todoDto.getContent());
        assertEquals(todo.isStatus(), todoDto.isStatus());
    }

    @Test
    void should_return_dto_todo_when_to_todo_dto_given_todo() {
        //given
        Todo todo = new Todo(1, "todo1", false);
        //when
        TodoDto todoDto = todoMapper.toTodoDto(todo);
        //then
        assertEquals(todoDto.getId(), todo.getId());
        assertEquals(todoDto.getContent(), todo.getContent());
        assertEquals(todoDto.isStatus(), todo.isStatus());
    }
}
