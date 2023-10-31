package com.icebear2n2.orderService.domain.request;

import com.icebear2n2.orderService.domain.entity.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderStatusRequest {
    private Long orderId;
    private OrderStatus orderStatus;
}
