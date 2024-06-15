package com.example.yetanotherbybitapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "t_orders")
@Entity
public class OrderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "isBuyerMaker")
    private Boolean isBuyerMaker;
}
