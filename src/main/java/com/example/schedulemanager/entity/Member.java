package com.example.schedulemanager.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Member {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime updatedDate;
}
