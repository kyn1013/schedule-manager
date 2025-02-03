package com.example.schedulemanager.common.response;

import com.example.schedulemanager.common.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {

    private HttpStatus status;
    private String message;

    public ErrorResponse(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode e) {
        return ResponseEntity.status(e.getStatus().value())
                        .body(ErrorResponse.builder()
                        .status(e.getStatus())
                        .message(e.getMessage())
                        .build());
    }
}
