package com.icebear2n2.orderService.domain.request;

import com.icebear2n2.orderService.domain.entity.Order;
import com.icebear2n2.orderService.domain.entity.OrderStatus;
import com.icebear2n2.orderService.domain.entity.User;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long userId;
    private Integer totalAmount;


    public Order toEntity(User user, Long trackingNumber) {
        return Order.builder()
                .user(user)
                .trackingNumber(trackingNumber)
                .status(OrderStatus.PENDING)
                .totalAmount(totalAmount)
                .build();
    }
}
