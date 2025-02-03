package com.example.schedulemanager.common.exception;

import com.example.schedulemanager.common.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;

@Getter
@RequiredArgsConstructor
public class InvalidRequestException extends RuntimeException {
    private final ErrorCode errorCode;
}
