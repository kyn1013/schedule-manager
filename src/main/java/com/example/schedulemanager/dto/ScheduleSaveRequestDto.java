package com.example.schedulemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {
    private String author;
    private String content;
    private String password;
}
