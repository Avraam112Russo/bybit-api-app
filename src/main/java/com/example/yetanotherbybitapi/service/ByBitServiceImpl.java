package com.example.yetanotherbybitapi.service;

import com.example.yetanotherbybitapi.model.ByBitResponse;
import com.example.yetanotherbybitapi.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ByBitServiceImpl implements ByBitService{
    private final String BASE_URL = "https://api.bybit.com";
    private final RestTemplate restTemplate;
    @Override
    public void getLastPrice(String symbol) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/v5/market/tickers")
                .queryParam("category", "spot")
                .queryParam("symbol", symbol);
        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);
        log.info(response);
    }

    @Override
    public ByBitResponse getLast100BtcOrders() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/spot/v3/public/quote/trades")
                .queryParam("symbol", "BTCUSDT")
                .queryParam("limit", 100);
        try {
        return restTemplate.getForObject(uriBuilder.toUriString(), ByBitResponse.class);

        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
