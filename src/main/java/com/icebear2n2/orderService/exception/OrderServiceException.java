package com.icebear2n2.orderService.exception;

import lombok.Getter;

@Getter
public class OrderServiceException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    public OrderServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
