package com.icebear2n2.orderService.domain.response;

import com.icebear2n2.orderService.domain.entity.cart.Cart;
import com.icebear2n2.orderService.domain.entity.cart.CartItem;
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
public class CartItemResponse {

    private boolean success;
    private String message;
    private CartItemData cartItemData;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CartItemData {
        private Cart cart;
        private Product product;
        private Integer quantity;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public CartItemData(CartItem cartItem) {
            this.cart = cartItem.getCart();
            this.product = cartItem.getProduct();
            this.quantity = cartItem.getQuantity();
            this.createdAt = cartItem.getCreatedAt();
            this.updatedAt = cartItem.getUpdatedAt();
        }
    }

    public static CartItemResponse success(CartItem cartItem) {
        return new CartItemResponse(true, "Success", new CartItemData(cartItem));
    }

    public static CartItemResponse failure(String message) {
        return new CartItemResponse(false, message, null);
    }
}
