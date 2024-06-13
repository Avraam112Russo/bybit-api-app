package com.example.yetanotherbybitapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @JsonProperty("price")
    private String price;
    @JsonProperty("time")
    private Number time;
    @JsonProperty("qty")
    private String qty;
    @JsonProperty("isBuyerMaker")
    private Integer isBuyerMaker;
    @JsonProperty("type")
    private String type;
}
