package com.example.yetanotherbybitapi.service;

import com.example.yetanotherbybitapi.model.ByBitResponse;
import com.example.yetanotherbybitapi.model.Order;
import com.example.yetanotherbybitapi.model.OrderDTO;
import com.example.yetanotherbybitapi.repository.ByBitApiRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ByBitServiceImpl implements ByBitService{
    private final String BASE_URL = "https://api.bybit.com";
    private final RestTemplate restTemplate;
    private final ByBitApiRepository byBitApiRepository;

    @Value("${coin.api.secretkey}")
    private String apiKey;
    @Override
    public void getLastPrice(String symbol) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/v5/market/tickers")
                .queryParam("category", "spot")
                .queryParam("symbol", symbol);
        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);
        log.info(response);
    }

    @Override
    public String getLast100BtcOrders(String symbol, Integer limit) {
        try {
          launchDataAnalyze(symbol, limit);
            return "Application launched successfully .";

        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void getLast100OrdersBySymbol(String symbol) {
        launchDataAnalyze(symbol, 100);
    }

    @Override
    @SneakyThrows
    public void getAllDataOrders(String symbol, Integer limit) {
        ByBitResponse byBitResponse = getByBitResponse(symbol, limit);
        Order[] list = byBitResponse.getResult().getList();
        Arrays.stream(list)
                .forEach(order -> {
                    OrderDTO orderDTO = OrderDTO.builder()
                            .price(Double.valueOf(order.getPrice()))
                            .quantity(Double.valueOf(order.getQty()))
                            .isBuyerMaker((order.getIsBuyerMaker() == 0) ? false : true)
                            .build();
                    byBitApiRepository.save(orderDTO);
                });
        log.info("All orders fetched successfully .");
       for (int i = 0; i < 5; i++){
        log.info(String.valueOf(i));
        Thread.sleep(1000);
       }
        getAllDataOrders(symbol, limit);
    }

    @Override
    public void getResultIsBuyerMaker() {
        List<OrderDTO> all = byBitApiRepository.findAll();
        List<Integer> buyList = new ArrayList<>();
        List<Integer> sellList = new ArrayList<>();
        all.stream()
                .forEach(order -> {
//                    if (order.getQuantity() > 0.5){
////                        log.info(order.toString())  ;
////                    }
                        if (order.getIsBuyerMaker() == false){
                            sellList.add(0);
                        }
                        else {
                            buyList.add(1);
                        }
                });
        log.info("BUY: " + buyList.size());
        log.info("SELL: " + sellList.size());
    }



//    @Override
//    public ResponseEntity<?> showLast100TradesFromCoinApiByExchangeAndSymbol(String symbol, int limit) {
//        // http://localhost:8081/api/v3/coinapi/trades?symbol=binance_SPOT_PEPE_USDT&limit=100
//        String coinApiTradesUrl = "https://rest.coinapi.io/v1/trades/" + symbol + "/latest?limit=" + limit;
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("X-CoinAPI-Key", apiKey);
//        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<?> response = restTemplate.exchange(coinApiTradesUrl, HttpMethod.GET, httpEntity, Object.class);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @Override
//    public ResponseEntity<?> getAllSymbolIds() {
//        String coinApiTradesUrl = "https://rest.coinapi.io/v1/symbols";
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("X-CoinAPI-Key", apiKey);
//        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<?> response = restTemplate.exchange(coinApiTradesUrl, HttpMethod.GET, httpEntity, Object.class);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }


    private void launchDataAnalyze(String symbol, Integer limit){
        ByBitResponse byBitResponse = getByBitResponse(symbol, limit);
        Order[] ordersList = byBitResponse.getResult().getList();
        getCountBuyAndSellOrders(ordersList);
        launchDataAnalyze(symbol, limit);
    }

    private ByBitResponse getByBitResponse(String symbol, Integer limit) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/spot/v3/public/quote/trades")
                .queryParam("symbol", symbol)
                .queryParam("limit", limit);
        ByBitResponse response = restTemplate.getForObject(uriBuilder.toUriString(), ByBitResponse.class);
        return response;
    }
    @SneakyThrows
    private void getCountBuyAndSellOrders(Order[] ordersList){
        List<Integer> buyList = new ArrayList<>();
        List<Integer> sellList = new ArrayList<>();
        Arrays.stream(ordersList)
                .forEach(order -> {
//                    if (Double.parseDouble(order.getQty()) > 0.5){
//                        log.info(order.toString())  ;
//                    }
                    if (order.getIsBuyerMaker() == 0){
                        sellList.add(0);
                    }
                    else {
                        buyList.add(1);
                    }
                });

        //            log.info("BUY: "+String.valueOf(buyList.size()));
//            log.info("SELL: "+String.valueOf(sellList.size()));
        boolean b = buyList.size() > sellList.size();
        log.info(String.valueOf((b == true ? "BUY DIRECTION" : "SELL DIRECTION")))  ;
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            log.info(String.valueOf(i));
        }
    }


}
