package com.example.yetanotherbybitapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlockChairServiceImpl implements BlockChairService{

    private final String BASE_URL = "https://api.blockchair.com/";
    private final RestTemplate restTemplate;
    @Override
    public ResponseEntity<?> getInfoAboutBtcTransactionByHash(String hash, String btcLikeChain) {
        UriComponentsBuilder uriComponentsBuilder =
                UriComponentsBuilder.fromHttpUrl(BASE_URL + btcLikeChain  +"/dashboards/transaction/" + hash);
        Object response = restTemplate.getForObject(uriComponentsBuilder.toUriString(), Object.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getFullStatsAboutBlockchainBySymbol(String symbol) {
        UriComponentsBuilder uriComponentsBuilder =
                UriComponentsBuilder.fromHttpUrl(BASE_URL + symbol + "/stats");
        Object response = restTemplate.getForObject(uriComponentsBuilder.toUriString(), Object.class);
        return ResponseEntity.ok(response);
    }
}
