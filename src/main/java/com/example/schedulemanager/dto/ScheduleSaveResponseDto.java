package com.example.schedulemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleSaveResponseDto {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
