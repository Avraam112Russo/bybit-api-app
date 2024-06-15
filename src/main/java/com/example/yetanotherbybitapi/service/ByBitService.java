package com.example.yetanotherbybitapi.service;

import com.example.yetanotherbybitapi.model.ByBitResponse;
import com.example.yetanotherbybitapi.model.Order;
import com.example.yetanotherbybitapi.model.OrderDTO;

import java.util.List;

public interface ByBitService {
    void getLastPrice(String symbol);
    String getLast100BtcOrders(String symbol, Integer limit);
    void getLast100OrdersBySymbol(String symbol);
    void getAllDataOrders(String symbol, Integer limit) ;

    void getResultIsBuyerMaker();
}
