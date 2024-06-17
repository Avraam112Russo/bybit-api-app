package com.example.yetanotherbybitapi.http.dataAnalyzeREST;

import com.example.yetanotherbybitapi.service.BlockChairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/bc")
@RequiredArgsConstructor
public class BlockChairApiRestController {
    private final BlockChairService blockChairService;


    @GetMapping("/transaction")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getInfoAboutBtcTransactionByHash(@RequestParam("hash")String hash,
                                                              @RequestParam("btcLikeChain")String btcLikeChain
                                                              ){
        return blockChairService.getInfoAboutBtcTransactionByHash(hash, btcLikeChain);
    }


    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getFullStatsAboutBlockchainBySymbol(@RequestParam("symbol")String symbol){
        return blockChairService.getFullStatsAboutBlockchainBySymbol(symbol);
    }
}
