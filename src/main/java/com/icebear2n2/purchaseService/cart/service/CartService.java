package com.icebear2n2.purchaseService.cart.service;

import com.icebear2n2.purchaseService.domain.entity.cart.Cart;
import com.icebear2n2.purchaseService.domain.entity.cart.CartItem;
import com.icebear2n2.purchaseService.domain.entity.product.Product;
import com.icebear2n2.purchaseService.domain.repository.CartItemRepository;
import com.icebear2n2.purchaseService.domain.repository.CartRepository;
import com.icebear2n2.purchaseService.domain.repository.ProductRepository;
import com.icebear2n2.purchaseService.domain.request.CartItemRequest;
import com.icebear2n2.purchaseService.domain.request.UpdateCartItemQuantityRequest;
import com.icebear2n2.purchaseService.domain.response.CartItemResponse;
import com.icebear2n2.purchaseService.exception.ErrorCode;
import com.icebear2n2.purchaseService.exception.PurcahseServiceException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    //        TODO: CREATE
    public CartItemResponse addCart(CartItemRequest cartItemRequest) {


        // TODO: 예외 처리: 장바구니 ID 가 존재하지 않는다면, 유저가 존재하지 않거나, 탈퇴된 유저일 경우가 있음. -> 유저가 존재한다면 절대 장바구니가 없을리 없음. 다시 로직을 자세히 설계할 필요가 있음 (중요)
        Cart cart = cartRepository.findById(cartItemRequest.getCartId()).orElseThrow(() -> new PurcahseServiceException(ErrorCode.INTERNAL_SERVER_ERROR));

        Product product = productRepository.findById(cartItemRequest.getProductId()).orElseThrow(() -> new PurcahseServiceException(ErrorCode.PRODUCT_NOT_FOUND));


        try {
            CartItem cartItem = cartItemRequest.toEntity(cart, product);

            CartItem saveCartItem = cartItemRepository.save(cartItem);
            return CartItemResponse.success(saveCartItem);
        } catch (Exception e) {
            return CartItemResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR.toString());
        }

    }

    //        TODO: READ
    public List<CartItemResponse.CartItemData> getCartItems(Long cartId) {

        // TODO: 예외 처리: 장바구니 ID 가 존재하지 않는다면, 유저가 존재하지 않거나, 탈퇴된 유저일 경우가 있음. -> 유저가 존재한다면 절대 장바구니가 없을리 없음. 다시 로직을 자세히 설계할 필요가 있음 (중요)
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new PurcahseServiceException(ErrorCode.INTERNAL_SERVER_ERROR));

        return cartItemRepository.findByCart(cart)
                .stream()
                .map(CartItemResponse.CartItemData::new)
                .collect(Collectors.toList());
    }

    //        TODO: UPDATE
    public CartItemResponse updateCartItemQuantity(UpdateCartItemQuantityRequest updateCartItemQuantityRequest) {

        // TODO: 예외 처리: 장바구니 ID 가 존재하지 않는다면, 유저가 존재하지 않거나, 탈퇴된 유저일 경우가 있음. -> 유저가 존재한다면 절대 장바구니가 없을리 없음. 다시 로직을 자세히 설계할 필요가 있음 (중요)
        cartRepository.findById(updateCartItemQuantityRequest.getCartId()).orElseThrow(() -> new PurcahseServiceException(ErrorCode.INTERNAL_SERVER_ERROR));

        try {
            CartItem existingCartItem = cartItemRepository.findById(updateCartItemQuantityRequest.getCartItemId())
                    .orElseThrow(() -> new PurcahseServiceException(ErrorCode.CART_ITEM_NOT_FOUND));

            updateCartItemQuantityRequest.updateCartItemIfNotNull(existingCartItem);
            cartItemRepository.save(existingCartItem);
            return CartItemResponse.success(existingCartItem);
        } catch (Exception e) {
            LOGGER.info("ERROR OCCURS {}", e.toString());
            return CartItemResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR.toString());
        }

    }


    //        TODO: DELETE
    public void removeCartItem(Long cartItemId) {
        if (!cartItemRepository.existsByCartItemId(cartItemId)) {
            throw new PurcahseServiceException(ErrorCode.CART_ITEM_NOT_FOUND);
        }

        try {
            cartItemRepository.deleteById(cartItemId);
        } catch (Exception e) {
            throw new PurcahseServiceException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: DELETE ALL

    public void removeCartItemAll(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new PurcahseServiceException(ErrorCode.INTERNAL_SERVER_ERROR));
        cartItemRepository.deleteByCart(cart);
    }
}
