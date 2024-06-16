package com.example.yetanotherbybitapi.service;

import org.springframework.http.ResponseEntity;

public interface ByBitService {
    void getLastPrice(String symbol);
    String getLast100BtcOrders(String symbol, Integer limit);
    void getLast100OrdersBySymbol(String symbol);
    void getAllDataOrders(String symbol, Integer limit) ;
    void getResultIsBuyerMaker();

}
