package com.example.schedulemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleSaveResponseDto {
    private Long id;
    private String content;
    private String author;
}
