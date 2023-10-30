package com.icebear2n2.orderService.order.service;

import com.icebear2n2.orderService.domain.entity.cart.CartItem;
import com.icebear2n2.orderService.domain.entity.order.Order;
import com.icebear2n2.orderService.domain.entity.order.OrderDetail;
import com.icebear2n2.orderService.domain.entity.product.Product;
import com.icebear2n2.orderService.domain.repository.CartItemRepository;
import com.icebear2n2.orderService.domain.repository.OrderDetailRepository;
import com.icebear2n2.orderService.domain.repository.OrderRepository;
import com.icebear2n2.orderService.domain.repository.ProductRepository;
import com.icebear2n2.orderService.domain.request.OrderDetailRequest;
import com.icebear2n2.orderService.domain.request.OrderRequest;
import com.icebear2n2.orderService.domain.request.UpdateOrderDetailItemQuantityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public void createOrder(OrderRequest orderRequest) {
        Order order = orderRequest.toEntity();
        List<CartItem> cartItems = cartItemRepository.findByOrder(order);
        order.setCartItems(cartItems);
        orderRepository.save(order);
    }

    public void createOrderDetail(OrderDetailRequest orderDetailRequest) {
        Order order = orderRepository.findById(orderDetailRequest.getOrderId()).orElseThrow(() -> new IllegalArgumentException("NOT FOUND!"));
        Product product = productRepository.findById(orderDetailRequest.getProductId()).orElseThrow(() -> new IllegalArgumentException("NOT FOUND!"));
        OrderDetail orderDetail = orderDetailRequest.toEntity(order, product);
        orderDetailRepository.save(orderDetail);
    }

    public void updateOrderDetailItemQuantity(UpdateOrderDetailItemQuantityRequest updateOrderDetailItemQuantityRequest) {
        OrderDetail orderDetail = orderDetailRepository.findById(updateOrderDetailItemQuantityRequest.getOrderDetailId()).orElseThrow(() -> new IllegalArgumentException("NOT FOUND!"));
        updateOrderDetailItemQuantityRequest.updateOrderDetailItemIfNotNull(orderDetail);
        orderDetailRepository.save(orderDetail);
    }

    public void removeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("NOT FOUND!"));
        order.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
    }

    public void removeOrderDetail(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(() -> new IllegalArgumentException("NOT FOUND!"));
        orderDetail.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        orderDetailRepository.save(orderDetail);
    }

}
