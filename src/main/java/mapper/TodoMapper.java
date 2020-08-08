package mapper;

import com.thoughtworks.todollist.dto.TodoDto;
import com.thoughtworks.todollist.model.Todo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    //TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    //todoDto -> todo
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "status", source = "status"),
    })
    Todo toTodoEntity(TodoDto todoDto);

    //todo -> todoDto
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "status", source = "status"),
    })
    TodoDto toTodoDto(Todo todo);
}
