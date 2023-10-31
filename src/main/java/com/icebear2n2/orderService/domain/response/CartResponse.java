package com.icebear2n2.orderService.domain.response;

import com.icebear2n2.orderService.domain.entity.cart.Cart;
import com.icebear2n2.orderService.domain.entity.cart.CartItem;
import com.icebear2n2.orderService.domain.entity.user.User;
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
public class CartResponse {
    private boolean success;
    private String message;
    private CartData cartData;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CartData {
        private Long cartId;
        private User user;
        private List<CartItem> cartItems;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public CartData(Cart cart) {
            this.cartId = cart.getCartId();
            this.user = cart.getUser();
            this.cartItems = cart.getCartItems();
            this.createdAt = cart.getCreatedAt();
            this.updatedAt = cart.getUpdatedAt();
        }
    }

    public static CartResponse success(Cart cart) {
        return new CartResponse(true, "Success", new CartData(cart));
    }

    public static CartResponse failure(String message) { return  new CartResponse(false, message, null);}
}
