package com.example.yetanotherbybitapi.http;

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
    public ResponseEntity<?> getLast100BtcOrders() {
        return ResponseEntity.ok(byBitService.getLast100BtcOrders());
    }
}
