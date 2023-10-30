package com.icebear2n2.orderService.domain.entity.order;

public enum OrderStatus {
    PENDING,  // 주문 진행 중
    SHIPPED,  // 배송 중
    DELIVERED, // 배송 완료
    CANCELED  // 주문 취소됨
}
