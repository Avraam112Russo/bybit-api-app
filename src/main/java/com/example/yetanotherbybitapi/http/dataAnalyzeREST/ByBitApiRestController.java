package com.example.yetanotherbybitapi.http.dataAnalyzeREST;

import com.example.yetanotherbybitapi.service.ByBitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ByBitApiRestController {

    private final ByBitService byBitService;
    @GetMapping("/btcPrice")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getLastPriceBySymbol(@RequestParam String symbol) {
        byBitService.getLastPrice(symbol);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/last100BtcOrders")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getLast100BtcOrders(@RequestParam(value = "symbol", required = false)String symbol,
                                                 @RequestParam(value = "limit", required = false)Integer limit) {
        return ResponseEntity.ok(byBitService.getLast100BtcOrders(symbol, limit));
    }
    @GetMapping("/last100OrdersBySymbol")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getLast100BtcOrders(@RequestParam(value = "symbol", required = true)String symbol) {
        byBitService.getLast100OrdersBySymbol(symbol);
        return ResponseEntity.ok("OK");
    }
    @GetMapping("/getAllDataOrders")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllDataOrders(@RequestParam("symbol")String symbol, @RequestParam("limit")Integer limit){
        byBitService.getAllDataOrders(symbol, limit);
        return ResponseEntity.ok("OK");
    }
}
