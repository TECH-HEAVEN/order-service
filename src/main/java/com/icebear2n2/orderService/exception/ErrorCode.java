package com.icebear2n2.orderService.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "PRODUCT NOT FOUND."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER NOT FOUND."),
    ORDER_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER DETAIL NOT FOUND."),
    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "CART ITEM NOT FOUND."),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "CART NOT FOUND."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER NOT FOUND."),
    INVALID_QUANTITY(HttpStatus.BAD_REQUEST, "INVALID QUANTITY.")
    ;
    private final HttpStatus httpStatus;
    private final String message;
}
