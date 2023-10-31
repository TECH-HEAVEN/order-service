package com.icebear2n2.orderService.domain.entity.order;

import com.icebear2n2.orderService.domain.entity.cart.CartItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Order")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long trackingNumber;
    @OneToMany(mappedBy = "order")
    private List<CartItem> cartItems;
    private Integer totalAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private OrderStatus status = OrderStatus.PENDING;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    private Timestamp deletedAt;
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
