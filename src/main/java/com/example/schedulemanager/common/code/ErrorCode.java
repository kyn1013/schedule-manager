package com.example.schedulemanager.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청 정보입니다."),
    INVALID_PASSWORD_EXCEPTION(HttpStatus.UNAUTHORIZED, "올바르지 않은 비밀번호입니다."),
    SCHEDULE_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다.");

    private final HttpStatus status;
    private final String message;
}
