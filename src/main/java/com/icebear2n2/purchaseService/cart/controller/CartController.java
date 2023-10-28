package com.icebear2n2.purchaseService.cart.controller;

import com.icebear2n2.purchaseService.cart.service.CartService;
import com.icebear2n2.purchaseService.domain.request.CartItemRequest;
import com.icebear2n2.purchaseService.domain.request.UpdateCartItemQuantityRequest;
import com.icebear2n2.purchaseService.domain.response.CartItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartItemResponse> addCart(@RequestBody CartItemRequest cartItemRequest) {
        CartItemResponse cartItemResponse = cartService.addCart(cartItemRequest);

        if (cartItemResponse.isSuccess()) {
            return new ResponseEntity<>(cartItemResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(cartItemResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getCartItems(@RequestBody Long cartId) {
        return new ResponseEntity<>(cartService.getCartItems(cartId), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CartItemResponse> updateCartItemQuantity(@RequestBody UpdateCartItemQuantityRequest updateCartItemQuantityRequest) {
        CartItemResponse cartItemResponse = cartService.updateCartItemQuantity(updateCartItemQuantityRequest);
        if (cartItemResponse.isSuccess()) {
            return new ResponseEntity<>(cartItemResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(cartItemResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> removeCartItem(@RequestBody Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return new ResponseEntity<>("Cart item removed successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> removeCartItemAll(@RequestBody Long cartId) {
        cartService.removeCartItemAll(cartId);
        return new ResponseEntity<>("All cart item removed successfully.", HttpStatus.OK);
    }
}
