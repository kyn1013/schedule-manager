package com.example.schedulemanager.common.util;

import com.example.schedulemanager.common.exception.InvalidRequestException;

import java.math.BigDecimal;

import static com.example.schedulemanager.common.code.ErrorCode.INVALID_REQUEST_EXCEPTION;

public class ValidationUtils {
    static final String NUMBER_REG = "[0-9]+";
    public static Long validNumberInputValue(String number) throws InvalidRequestException {
        if (!number.matches(NUMBER_REG)){
            throw new InvalidRequestException(INVALID_REQUEST_EXCEPTION);
        }
        return Long.parseLong(number);
    }

    public static BigDecimal convertToBigDecimal(Number number) {
        return new BigDecimal(number.toString());
    }
}
