package com.example.yetanotherbybitapi.service;

import org.springframework.http.ResponseEntity;

public interface BlockChairService {
    ResponseEntity<?> getInfoAboutBtcTransactionByHash(String hash, String btcLikeChain);
    ResponseEntity<?> getFullStatsAboutBlockchainBySymbol(String btcLikeChain);
}
