package com.example.yetanotherbybitapi.service;

import com.example.yetanotherbybitapi.model.ByBitResponse;
import com.example.yetanotherbybitapi.model.Order;

import java.util.List;

public interface ByBitService {
    void getLastPrice(String symbol);
    ByBitResponse getLast100BtcOrders();
}
