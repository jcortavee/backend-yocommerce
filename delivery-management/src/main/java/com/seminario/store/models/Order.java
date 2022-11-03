package com.seminario.store.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    @JsonProperty(value = "user_id")
    private Long userId;

    @Column(name = "store_id")
    @JsonProperty(value = "store_id")
    private Long storeId;

    @Column(name = "address_id")
    @JsonProperty(value = "address_id")
    private Long addressId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_status_id")
    @JsonProperty(value = "order_status")
    private OrderStatus orderStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    @JsonProperty(value = "order_details")
    private List<OrderDetails> orderDetails;

    @JsonProperty(value = "order_number")
    private String orderNumber;

    @Column(name = "created_at")
    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    private String comment;

}
