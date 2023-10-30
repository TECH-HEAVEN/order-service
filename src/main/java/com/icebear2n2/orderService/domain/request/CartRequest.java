package com.icebear2n2.purchaseService.domain.request;

import com.icebear2n2.purchaseService.domain.entity.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    // TODO: 사용자가 생성 시 같이 생성?...
    public Cart toEntity() {
        return Cart.builder().build();
    }
}
