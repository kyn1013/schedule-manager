package com.example.schedulemanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String content;
    private Long memberId;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public Schedule(String content, Long memberId, String password){
        this.content = content;
        this.memberId = memberId;
        this.password = password;
    }

    public Schedule(Long id, String content, Long memberId, LocalDateTime createDate, LocalDateTime modifiedDate){
        this.id = id;
        this.content = content;
        this.memberId = memberId;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

}
