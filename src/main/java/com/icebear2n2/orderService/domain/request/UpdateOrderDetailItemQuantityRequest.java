package com.icebear2n2.orderService.domain.request;

import com.icebear2n2.orderService.domain.entity.order.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDetailItemQuantityRequest {
    private Long orderDetailId;
    private Long productId;
    private Integer quantity;

    public void updateOrderDetailItemIfNotNull(OrderDetail orderDetail) {
        if (this.quantity != null) {
            orderDetail.setQuantity(this.quantity);
        }
    }
}
