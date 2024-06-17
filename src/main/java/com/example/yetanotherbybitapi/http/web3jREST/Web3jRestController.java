package com.example.yetanotherbybitapi.http.web3jREST;

import com.example.yetanotherbybitapi.service.web3j.Web3JService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.EthGetBalance;

@RestController
@RequestMapping("/api/v4/web3j")
@RequiredArgsConstructor
public class Web3jRestController {
    private final Web3JService web3JService;

    @GetMapping("/balance")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getBalanceByAddress(@RequestParam("address")String address){
        EthGetBalance ethBalance = web3JService.getEthBalance(address);
        return new ResponseEntity<>(ethBalance, HttpStatus.OK);
    }
    @GetMapping("/new-eth-wallet")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> createNewEthAddress(@RequestParam("keyword")String keyword){
        String address = web3JService.createNewEthAddress(keyword);
        return new ResponseEntity<>("Your ETH address complete: %s".formatted(address), HttpStatus.OK);
    }
    @GetMapping("/latest-block")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getLastBlock(){
        return new ResponseEntity<>(web3JService.getLastBlock(), HttpStatus.OK);
    }
}
