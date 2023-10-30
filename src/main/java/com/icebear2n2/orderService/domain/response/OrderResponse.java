package com.icebear2n2.orderService.domain.response;

import com.icebear2n2.orderService.domain.entity.cart.CartItem;
import com.icebear2n2.orderService.domain.entity.order.Order;
import com.icebear2n2.orderService.domain.entity.order.OrderDetail;
import com.icebear2n2.orderService.domain.entity.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private boolean success;
    private String message;
    private OrderData orderData;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderData {
        private Long orderId;
        private List<CartItem> cartItems;
        private Integer totalAmount;
        private OrderStatus status;
        private List<OrderDetail> orderDetails;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private Timestamp deletedAt;

        public OrderData(Order order) {
            this.orderId = order.getOrderId();
            this.cartItems = order.getCartItems();
            this.totalAmount = order.getTotalAmount();
            this.status = order.getStatus();
            this.orderDetails = order.getOrderDetails();
            this.createdAt = order.getCreatedAt();
            this.updatedAt = order.getUpdatedAt();
            this.deletedAt = order.getDeletedAt();
        }
    }

    public static OrderResponse success(Order order) {
        return new OrderResponse(true, "Success", new OrderData(order));
    }

    public static OrderResponse failure(String message) { return new OrderResponse(false, message, null); }
}
