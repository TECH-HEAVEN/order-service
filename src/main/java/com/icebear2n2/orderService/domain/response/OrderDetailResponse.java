package com.icebear2n2.orderService.domain.response;

import com.icebear2n2.orderService.domain.entity.order.Order;
import com.icebear2n2.orderService.domain.entity.order.OrderDetail;
import com.icebear2n2.orderService.domain.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {

    private boolean success;
    private String message;
    private OrderDetailData orderDetailData;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderDetailData {
        private Long orderDetailId;
        private Order order;
        private Product product;
        private Integer quantity;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private Timestamp deletedAt;

        public OrderDetailData(OrderDetail orderDetail) {
            this.orderDetailId = orderDetail.getOrderDetailId();
            this.order = orderDetail.getOrder();
            this.product = orderDetail.getProduct();
            this.quantity = orderDetail.getQuantity();
            this.createdAt = orderDetail.getCreatedAt();
            this.updatedAt = orderDetail.getUpdatedAt();
            this.deletedAt = orderDetail.getDeletedAt();
        }
    }

    public static OrderDetailResponse success(OrderDetail orderDetail) {
        return new OrderDetailResponse(true, "Success", new OrderDetailData(orderDetail));
    }

    public static OrderDetailResponse failure(String message) { return new OrderDetailResponse(false, message, null); }
}
