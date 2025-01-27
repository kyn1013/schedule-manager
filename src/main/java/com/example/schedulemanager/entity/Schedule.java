package com.example.schedulemanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String content;
    private String author;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public Schedule(String content, String author, String password){
        this.content = content;
        this.author = author;
        this.password = password;
    }

    public Schedule(Long id, String content, String author, LocalDateTime createDate, LocalDateTime modifiedDate){
        this.id = id;
        this.content = content;
        this.author = author;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

}
