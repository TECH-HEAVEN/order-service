package com.icebear2n2.orderService.order.service;

import com.icebear2n2.orderService.domain.entity.Order;
import com.icebear2n2.orderService.domain.entity.OrderDetail;
import com.icebear2n2.orderService.domain.entity.Product;
import com.icebear2n2.orderService.domain.repository.OrderDetailRepository;
import com.icebear2n2.orderService.domain.repository.OrderRepository;
import com.icebear2n2.orderService.domain.repository.ProductRepository;
import com.icebear2n2.orderService.domain.request.OrderDetailRequest;
import com.icebear2n2.orderService.domain.request.OrderIDRequest;
import com.icebear2n2.orderService.domain.request.UpdateOrderDetailItemQuantityRequest;
import com.icebear2n2.orderService.domain.response.OrderDetailResponse;
import com.icebear2n2.orderService.exception.ErrorCode;
import com.icebear2n2.orderService.exception.OrderServiceException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetailService.class);
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    public OrderDetailResponse createOrderDetail(OrderDetailRequest orderDetailRequest) {
        Order order = orderRepository.findById(orderDetailRequest.getOrderId())
                .orElseThrow(() -> new OrderServiceException(ErrorCode.ORDER_NOT_FOUND));
        Product product = productRepository.findById(orderDetailRequest.getProductId()).orElseThrow(() -> new OrderServiceException(ErrorCode.PRODUCT_NOT_FOUND));
        try {
            OrderDetail orderDetail = orderDetailRequest.toEntity(order, product);
            OrderDetail saveOrderDetail = orderDetailRepository.save(orderDetail);


            return OrderDetailResponse.success(saveOrderDetail);
        } catch (Exception e) {
            LOGGER.info("INTERNAL_SERVER_ERROR: {}", e.toString());
            return OrderDetailResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR.toString());
        }

    }

    public Page<OrderDetailResponse.OrderDetailData> getAllByOrder(OrderIDRequest orderIDRequest, PageRequest pageRequest) {
        Order order = orderRepository.findById(orderIDRequest.getOrderId())
                .orElseThrow(() -> new OrderServiceException(ErrorCode.ORDER_NOT_FOUND));

        Page<OrderDetail> all = orderDetailRepository.findAllByOrder(order, pageRequest);
        return all.map(OrderDetailResponse.OrderDetailData::new);
    }

    public OrderDetailResponse updateOrderDetailItemQuantity(UpdateOrderDetailItemQuantityRequest updateOrderDetailItemQuantityRequest) {
        Order order = orderRepository.findById(updateOrderDetailItemQuantityRequest.getOrderId())
                .orElseThrow(() -> new OrderServiceException(ErrorCode.ORDER_NOT_FOUND));

        try {
            OrderDetail orderDetail = order.getOrderDetails().stream()
                    .filter(detail -> detail.getProduct().getProductId().equals(updateOrderDetailItemQuantityRequest.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new OrderServiceException(ErrorCode.ORDER_DETAIL_NOT_FOUND));



            if (updateOrderDetailItemQuantityRequest.getQuantity() != null) {
                orderDetail.setQuantity(updateOrderDetailItemQuantityRequest.getQuantity());
                OrderDetail updatedOrderDetail = orderDetailRepository.save(orderDetail);
                return OrderDetailResponse.success(updatedOrderDetail);
            } else {
                return OrderDetailResponse.failure(ErrorCode.INVALID_QUANTITY.toString());
            }
        } catch (Exception e) {
            LOGGER.info("INTERNAL_SERVER_ERROR: {}", e.toString());
            return OrderDetailResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR.toString());
        }
    }
}

