package com.thoughtworks.todollist.exception;

public class NotExistTodoException extends Exception {
    private final String errorMessage;

    public NotExistTodoException(ExceptionMessage notExistsEmployee) {
        this.errorMessage = notExistsEmployee.getValue();
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
