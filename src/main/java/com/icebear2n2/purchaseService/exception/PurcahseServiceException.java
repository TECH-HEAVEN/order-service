package com.icebear2n2.purchaseService.exception;

import lombok.Getter;

@Getter
public class PurcahseServiceException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    public PurcahseServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
