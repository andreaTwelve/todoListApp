package com.thoughtworks.todolist.integration;

import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodoIntegrationTest {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void after() {
        todoRepository.deleteAll();
    }

    @Test
    public void should_return_todo_when_add_todo_given_todo() throws Exception {
        //given
        String jsonTodo = "{\n" +
                "        \"id\": 2,\n" +
                "        \"content\": \"klkl\",\n" +
                "        \"status\": true\n" +
                "    }";
        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content(jsonTodo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("klkl"))
                .andExpect(jsonPath("$.status").value("true"));
    }

    @Test
    public void should_return_todos_when_get_todos_given_no_parameters() throws Exception {
        todoRepository.save(new Todo(1, "oocl1", false));
        todoRepository.save(new Todo(2, "oocl2", false));
        todoRepository.save(new Todo(3, "oocl3", false));

        mockMvc.perform(get("/todos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].content").value("oocl1"))
                .andExpect(jsonPath("$[0].status").value(false));
    }

    @Test
    void should_return_none_when_delete_todo_by_id_given_todo_id() throws Exception {
        //given
        Todo todo = new Todo(1, "oocl1", false);
        Todo newTodo = todoRepository.save(todo);


        mockMvc.perform(delete("/todos/{id}", newTodo.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //when//then
    }

//    @Test
//    void should_return_updated_todo_when_update_todo_given_todo_id() throws Exception {
//        Todo todo = todoRepository.save(new Todo(1, "oocl1", false));
//        String jsonTodo = "{\n" +
//                "    \"id\": 1,\n" +
//                "    \"content\": \"oocl1\",\n" +
//                "    \"status\": false\n" +
//                "}";
//        mockMvc.perform(put("/todos/{id}", todo.getId()).contentType(MediaType.APPLICATION_JSON).content(jsonTodo))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("klkl"))
//                .andExpect(jsonPath("$.status").value("true"));
//    }
}
