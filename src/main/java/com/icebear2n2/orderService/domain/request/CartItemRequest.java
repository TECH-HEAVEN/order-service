package com.icebear2n2.orderService.domain.request;

import com.icebear2n2.orderService.domain.entity.cart.Cart;
import com.icebear2n2.orderService.domain.entity.cart.CartItem;
import com.icebear2n2.orderService.domain.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    private Long cartId;
    private Long productId;
    private Integer quantity;

    public CartItem toEntity(Cart cart, Product product) {
        return CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .build();
    }
}
