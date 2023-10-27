package com.icebear2n2.purchaseService.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartItemQuantityRequest {
    private Long cartItemId;
    private Integer quantity;
}
