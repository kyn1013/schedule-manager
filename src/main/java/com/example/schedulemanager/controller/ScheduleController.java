package com.example.schedulemanager.controller;

import com.example.schedulemanager.common.exception.InvalidRequestException;
import com.example.schedulemanager.common.exception.ScheduleNotFoundException;
import com.example.schedulemanager.dto.SchedulePagingResponseDto;
import com.example.schedulemanager.dto.ScheduleRequestDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.schedulemanager.common.code.ErrorCode.SCHEDULE_NOT_FOUND_EXCEPTION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(
            @RequestParam("member_id") String memberId,
            @RequestParam("updated_at") String modifiedDate
    ){
        return new ResponseEntity<>(scheduleService.findAllSchedules(memberId, modifiedDate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable String id) {
        return new ResponseEntity<>(scheduleService.findByScheduleId(id), HttpStatus.OK);
    }

    @GetMapping("/members")
    public ResponseEntity<List<ScheduleResponseDto>> findScheduleByMemberId(@RequestParam("member_id") String memberId) {

        return new ResponseEntity<>(scheduleService.findByScheduleMemberId(memberId), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable String id, @RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, requestDto.getPassword(), requestDto.getContent(), requestDto.getMemberId()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable String id, @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.deleteSchedule(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<SchedulePagingResponseDto>> list(@RequestParam("page") int page, @RequestParam("size") int size){
        Page<SchedulePagingResponseDto> list = scheduleService.list(page, size);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
