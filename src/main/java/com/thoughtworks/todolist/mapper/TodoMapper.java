package com.thoughtworks.todolist.mapper;

import com.thoughtworks.todolist.dto.TodoDto;
import com.thoughtworks.todolist.model.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    //TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);
    //todoDto -> todo
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "status", target = "status"),
    })
    Todo toTodoEntity(TodoDto todoDto);

    //todo -> todoDto
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "status", target = "status"),
    })
    TodoDto toTodoDto(Todo todo);
    List<TodoDto> toTodoDtoList(List<Todo> todoList);
}
