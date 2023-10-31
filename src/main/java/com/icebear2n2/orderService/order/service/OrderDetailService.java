package com.icebear2n2.orderService.order.service;

import com.icebear2n2.orderService.domain.entity.order.Order;
import com.icebear2n2.orderService.domain.entity.order.OrderDetail;
import com.icebear2n2.orderService.domain.entity.product.Product;
import com.icebear2n2.orderService.domain.repository.OrderDetailRepository;
import com.icebear2n2.orderService.domain.repository.OrderRepository;
import com.icebear2n2.orderService.domain.repository.ProductRepository;
import com.icebear2n2.orderService.domain.request.OrderDetailRequest;
import com.icebear2n2.orderService.domain.request.UpdateOrderDetailItemQuantityRequest;
import com.icebear2n2.orderService.domain.response.OrderDetailResponse;
import com.icebear2n2.orderService.exception.ErrorCode;
import com.icebear2n2.orderService.exception.OrderServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    public OrderDetailResponse createOrderDetail(OrderDetailRequest orderDetailRequest) {
        Order order = orderRepository.findById(orderDetailRequest.getOrderId())
                .orElseThrow(() -> new OrderServiceException(ErrorCode.ORDER_NOT_FOUND));
        Product product = productRepository.findById(orderDetailRequest.getProductId())
                .orElseThrow(() -> new OrderServiceException(ErrorCode.PRODUCT_NOT_FOUND));

        try {
            OrderDetail orderDetail = orderDetailRequest.toEntity(order, product);
            OrderDetail saveOrderDetail = orderDetailRepository.save(orderDetail);
            return OrderDetailResponse.success(saveOrderDetail);
        } catch (Exception e) {
            return OrderDetailResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR.toString());
        }

    }

    public OrderDetailResponse updateOrderDetailItemQuantity(UpdateOrderDetailItemQuantityRequest updateOrderDetailItemQuantityRequest) {
        OrderDetail orderDetail = orderDetailRepository.findById(updateOrderDetailItemQuantityRequest.getOrderDetailId())
                .orElseThrow(() -> new OrderServiceException(ErrorCode.ORDER_DETAIL_NOT_FOUND));

        try {
            updateOrderDetailItemQuantityRequest.updateOrderDetailItemIfNotNull(orderDetail);
            OrderDetail updateOrderDetail = orderDetailRepository.save(orderDetail);
            return OrderDetailResponse.success(updateOrderDetail);
        } catch (Exception e) {
            return OrderDetailResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR.toString());
        }
    }
}
