package com.example.schedulemanager.controller;

import com.example.schedulemanager.common.code.ErrorCode;
import com.example.schedulemanager.common.exception.InvalidRequestException;
import com.example.schedulemanager.common.exception.InvalidPasswordException;
import com.example.schedulemanager.common.exception.ScheduleNotFoundException;
import com.example.schedulemanager.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice(assignableTypes = ScheduleController.class)
@Slf4j
public class ScheduleExceptionController {

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleScheduleNotFoundException(ScheduleNotFoundException e) {
        log.error("[ScheduleExceptionHandler] ScheduleNotFoundException = {}, class = {}", e.getErrorCode().getMessage(), e.getClass());
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException e) {
        log.error("[InvalidPasswordExceptionHandler] InvalidPasswordException = {}, class = {}", e.getErrorCode().getMessage(), e.getClass());
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException e) {
        log.error("[InvalidRequestExceptionHandler] InvalidRequestException = {}, class = {}", e.getErrorCode().getMessage(), e.getClass());
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage()));
    }
}
