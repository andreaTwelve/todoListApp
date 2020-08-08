package com.thoughtworks.todolist.mapper;

import mapper.TodoMapper;
import com.thoughtworks.todollist.dto.TodoDto;
import com.thoughtworks.todollist.model.Todo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;
@SpringBootTest(classes = TodoMapper.class)
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
        assertEquals(todoDto, todo);
    }
}
