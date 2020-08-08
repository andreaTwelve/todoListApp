package com.thoughtworks.todollist.dto;

import lombok.Data;

@Data
public class TodoDto {
    private Integer id;
    private String content;
    private boolean status;

    public TodoDto() {
    }

    public TodoDto(Integer id, String content, boolean status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }
}
