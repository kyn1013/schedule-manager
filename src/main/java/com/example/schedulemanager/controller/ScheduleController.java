package com.example.schedulemanager.controller;

import com.example.schedulemanager.dto.ScheduleSaveRequestDto;
import com.example.schedulemanager.dto.ScheduleSaveResponseDto;
import com.example.schedulemanager.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleSaveResponseDto> createSchedule(@RequestBody ScheduleSaveRequestDto saveRequestDto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(saveRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleSaveResponseDto>> findAllSchedules(
            @RequestParam("author") String author,
            @RequestParam("updated_at") String modifiedDate
    ){
        return new ResponseEntity<>(scheduleService.findAllSchedules(author, modifiedDate), HttpStatus.OK);
    }
}
