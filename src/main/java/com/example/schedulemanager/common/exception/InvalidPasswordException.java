package com.example.schedulemanager.common.exception;

import com.example.schedulemanager.common.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvalidPasswordException extends RuntimeException{
    private final ErrorCode errorCode;
}
