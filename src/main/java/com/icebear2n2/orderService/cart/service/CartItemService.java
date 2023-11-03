package com.icebear2n2.orderService.cart.service;

import com.icebear2n2.orderService.domain.entity.Cart;
import com.icebear2n2.orderService.domain.entity.CartItem;
import com.icebear2n2.orderService.domain.entity.Product;
import com.icebear2n2.orderService.domain.repository.CartItemRepository;
import com.icebear2n2.orderService.domain.repository.CartRepository;
import com.icebear2n2.orderService.domain.repository.ProductRepository;
import com.icebear2n2.orderService.domain.request.CartItemRequest;
import com.icebear2n2.orderService.domain.request.UpdateCartItemQuantityRequest;
import com.icebear2n2.orderService.domain.response.CartItemResponse;
import com.icebear2n2.orderService.exception.ErrorCode;
import com.icebear2n2.orderService.exception.OrderServiceException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartItemService.class);
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartItemResponse addCart(CartItemRequest cartItemRequest) {
        if (!cartRepository.existsByCartId(cartItemRequest.getCartId())) {
            return CartItemResponse.failure(ErrorCode.CART_NOT_FOUND.toString());
        }
        if (!productRepository.existsByProductId(cartItemRequest.getProductId())) {
            return CartItemResponse.failure(ErrorCode.PRODUCT_NOT_FOUND.toString());
        }

        try {
            Cart cart = cartRepository.findById(cartItemRequest.getCartId()).orElseThrow(() -> new OrderServiceException(ErrorCode.INTERNAL_SERVER_ERROR));
            Product product = productRepository.findById(cartItemRequest.getProductId()).orElseThrow(() -> new OrderServiceException(ErrorCode.PRODUCT_NOT_FOUND));

            CartItem cartItem = cartItemRequest.toEntity(cart, product);

            CartItem saveCartItem = cartItemRepository.save(cartItem);
            return CartItemResponse.success(saveCartItem);
        } catch (Exception e) {
            LOGGER.info("INTERNAL_SERVER_ERROR: {}", e.toString());
            return CartItemResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR.toString());
        }

    }

    //        TODO: READ
    public List<CartItemResponse.CartItemData> getCartItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new OrderServiceException(ErrorCode.INTERNAL_SERVER_ERROR));

        return cartItemRepository.findByCart(cart)
                .stream()
                .map(CartItemResponse.CartItemData::new)
                .collect(Collectors.toList());
    }

    //        TODO: UPDATE
    public CartItemResponse updateCartItemQuantity(UpdateCartItemQuantityRequest updateCartItemQuantityRequest) {

        cartRepository.findById(updateCartItemQuantityRequest.getCartId()).orElseThrow(() -> new OrderServiceException(ErrorCode.INTERNAL_SERVER_ERROR));

        try {
            CartItem existingCartItem = cartItemRepository.findById(updateCartItemQuantityRequest.getCartItemId())
                    .orElseThrow(() -> new OrderServiceException(ErrorCode.CART_ITEM_NOT_FOUND));

            updateCartItemQuantityRequest.updateCartItemIfNotNull(existingCartItem);
            cartItemRepository.save(existingCartItem);
            return CartItemResponse.success(existingCartItem);
        } catch (Exception e) {
            LOGGER.info("INTERNAL_SERVER_ERROR: {}", e.toString());
            return CartItemResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR.toString());
        }

    }


    //        TODO: DELETE
    public void removeCartItem(Long cartItemId) {
        if (!cartItemRepository.existsByCartItemId(cartItemId)) {
            throw new OrderServiceException(ErrorCode.CART_ITEM_NOT_FOUND);
        }

        try {
            cartItemRepository.deleteById(cartItemId);
        } catch (Exception e) {
            LOGGER.info("INTERNAL_SERVER_ERROR: {}", e.toString());
            throw new OrderServiceException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: DELETE ALL

    public void removeCartItemAll(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new OrderServiceException(ErrorCode.INTERNAL_SERVER_ERROR));
        cartItemRepository.deleteByCart(cart);
    }
}
