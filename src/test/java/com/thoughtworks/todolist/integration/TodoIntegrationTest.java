package com.thoughtworks.todolist.integration;

import com.thoughtworks.todollist.repository.TodoRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .andExpect((ResultMatcher) jsonPath("$.id").isNumber())
                .andExpect((ResultMatcher) jsonPath("$.content").value("klkl"))
                .andExpect((ResultMatcher) jsonPath("$.status").value("true"));
    }
}
