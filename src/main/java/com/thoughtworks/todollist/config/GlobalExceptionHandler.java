package com.thoughtworks.todollist.config;

import com.thoughtworks.todollist.exception.NotExistTodoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(value = NotExistTodoException.class)
    public String notExistEmployeeHandler(NotExistTodoException notExistTodoException) {
        return notExistTodoException.getMessage();
    }
}
