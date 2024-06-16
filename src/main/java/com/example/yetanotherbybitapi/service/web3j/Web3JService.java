package com.example.yetanotherbybitapi.service.web3j;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.File;

@Service
@Slf4j
public class Web3JService {

    private Web3j web3j;

    @SneakyThrows
    public void createNewEthAddress(){
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        String walletFileName = WalletUtils.generateWalletFile("my_password", ecKeyPair, new File("."), false);
        Credentials credentials = WalletUtils.loadCredentials("my_password", walletFileName);
        System.out.println("New address: " + credentials.getAddress());
    }
    @SneakyThrows
    public EthBlockNumber getBlockNumber() {
        web3j = Web3j.build(new HttpService());
        EthBlockNumber result = new EthBlockNumber();
        result = this.web3j.ethBlockNumber()
                .sendAsync()
                .get();
        return result;
    }

    @SneakyThrows
    public EthAccounts getEthAccounts() {
        EthAccounts result = new EthAccounts();
        result = this.web3j.ethAccounts()
                .sendAsync()
                .get();
        return result;
    }
    @SneakyThrows
    public EthGetTransactionCount getTransactionCount() {
        String DEFAULT_ADDRESS = null;
        EthGetTransactionCount result = new EthGetTransactionCount();
        result = this.web3j.ethGetTransactionCount(DEFAULT_ADDRESS,
                        DefaultBlockParameter.valueOf("latest"))
                .sendAsync()
                .get();
        return result;
    }
    @SneakyThrows
    public EthGetBalance getEthBalance(String address) {
        EthGetBalance result = new EthGetBalance();
        this.web3j.ethGetBalance(address,
                        DefaultBlockParameter.valueOf("latest"))
                .sendAsync()
                .get();
        log.info(result.getResult());
        log.info(result.getBalance().toString());
        return result;
    }
}
