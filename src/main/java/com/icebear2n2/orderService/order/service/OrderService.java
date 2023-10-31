package com.icebear2n2.orderService.order.service;

import com.icebear2n2.orderService.domain.entity.cart.CartItem;
import com.icebear2n2.orderService.domain.entity.order.Order;
import com.icebear2n2.orderService.domain.entity.order.OrderDetail;
import com.icebear2n2.orderService.domain.entity.user.User;
import com.icebear2n2.orderService.domain.repository.CartItemRepository;
import com.icebear2n2.orderService.domain.repository.OrderDetailRepository;
import com.icebear2n2.orderService.domain.repository.OrderRepository;

import com.icebear2n2.orderService.domain.repository.UserRepository;
import com.icebear2n2.orderService.domain.request.OrderRequest;

import com.icebear2n2.orderService.domain.request.UpdateOrderStatusRequest;

import com.icebear2n2.orderService.domain.response.OrderResponse;
import com.icebear2n2.orderService.exception.ErrorCode;
import com.icebear2n2.orderService.exception.OrderServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final Random random;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new OrderServiceException(ErrorCode.USER_NOT_FOUND));

        Order order = orderRequest.toEntity(user, generateTrackingNumber());
        List<CartItem> cartItems = cartItemRepository.findByOrder(order);

        if (cartItems.isEmpty()) {
            return OrderResponse.failure(ErrorCode.CART_ITEM_NOT_FOUND.toString());
        }

        try {
            order.setCartItems(cartItems);
            Order saveOrder = orderRepository.save(order);
            return OrderResponse.success(saveOrder);
        } catch (Exception e) {
            return OrderResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR.toString());
        }

    }


    public OrderResponse changeOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest) {
        Order order = orderRepository.findById(updateOrderStatusRequest.getOrderId())
                .orElseThrow(() -> new OrderServiceException(ErrorCode.ORDER_NOT_FOUND));

        try {
            order.setStatus(updateOrderStatusRequest.getOrderStatus());
            Order updateOrder = orderRepository.save(order);
            return OrderResponse.success(updateOrder);
        } catch (Exception e) {
            return OrderResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR.toString());
        }

    }

    /**
     *  유저 탈퇴 시, 같이 삭제
     * */
    public void removeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderServiceException(ErrorCode.ORDER_NOT_FOUND));

        order.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
    }

    public void removeOrderDetail(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(() -> new OrderServiceException(ErrorCode.ORDER_DETAIL_NOT_FOUND));
        orderDetail.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        orderDetailRepository.save(orderDetail);
    }


    /**
    * 주문 번호 12자리 랜덤으로 생성
     */
    private Long generateTrackingNumber() {
        long trackingNumber;
        do {
            trackingNumber = 100000000000L + random.nextLong(900000000000L);
        } while (orderRepository.findByTrackingNumber(trackingNumber) != null);
        return trackingNumber;
    }
}
