package com.icebear2n2.purchaseService.cart.service;

import com.icebear2n2.purchaseService.domain.repository.CartItemRepository;
import com.icebear2n2.purchaseService.domain.repository.CartRepository;
import com.icebear2n2.purchaseService.domain.repository.ProductRepository;
import com.icebear2n2.purchaseService.domain.request.CartItemRequest;
import com.icebear2n2.purchaseService.domain.request.UpdateCartItemQuantityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public void addCart(CartItemRequest cartItemRequest) {
//        TODO: CREATE
    }

    public void getCartItems() {
//        TODO: READ
    }

    public void updateCartItemQuantity(UpdateCartItemQuantityRequest updateCartItemQuantityRequest) {
//        TODO: UPDATE
    }

    public void deleteCartItem(Long cartItemId) {
//        TODO: DELETE
    }
}
