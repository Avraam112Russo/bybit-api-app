package com.example.yetanotherbybitapi.http;

import com.example.yetanotherbybitapi.service.ByBitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class HandleDataRestController {
    private final ByBitService byBitService;
    @GetMapping("/getResultIsBuyerMaker")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getResultIsBuyerMaker(){
        byBitService.getResultIsBuyerMaker();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
