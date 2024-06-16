package com.example.yetanotherbybitapi.http;

import com.example.yetanotherbybitapi.service.web3j.Web3JService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v4/web3j")
@RequiredArgsConstructor
public class Web3jRestController {
    private final Web3JService web3JService;

    @GetMapping("/balance")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getBalanceByAddress(@RequestParam("address")String address){
        web3JService.getEthBalance(address).getBalance();
        return new ResponseEntity<>("Successfully!", HttpStatus.OK);
    }
    @GetMapping("/new-eth-wallet")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> createNewEthAddress(){
        web3JService.createNewEthAddress();
        return new ResponseEntity<>("Successfully!", HttpStatus.OK);
    }
}
