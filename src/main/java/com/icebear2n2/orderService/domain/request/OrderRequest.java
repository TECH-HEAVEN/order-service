package com.icebear2n2.orderService.domain.request;

import com.icebear2n2.orderService.domain.entity.order.Order;
import com.icebear2n2.orderService.domain.entity.order.OrderStatus;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Integer totalAmount;
    private OrderStatus status;

    public Order toEntity(Long trackingNumber) {
        return Order.builder()
                .trackingNumber(trackingNumber)
                .totalAmount(totalAmount)
                .status(status)
                .build();
    }
}
