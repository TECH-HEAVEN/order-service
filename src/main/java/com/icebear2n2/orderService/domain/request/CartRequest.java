package com.icebear2n2.orderService.domain.request;

import com.icebear2n2.orderService.domain.entity.cart.Cart;
import com.icebear2n2.orderService.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private Long userId;

    public Cart toEntity(User user) {
        return Cart
                .builder()
                .user(user)
                .build();
    }
}
