package com.example.schedulemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SchedulePagingResponseDto {
    private Long id;
    private String content;
    private String memberName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
