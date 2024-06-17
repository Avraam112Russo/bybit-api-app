package com.example.yetanotherbybitapi.service.web3j;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class Web3JService {
    private final Web3j web3j;
    @SneakyThrows
    public Credentials createNewEthAddress(String keyWord){
        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            String walletFileName = WalletUtils.generateWalletFile(keyWord, ecKeyPair, new File("."), false);
            Credentials credentials = WalletUtils.loadCredentials(keyWord, walletFileName);
            String address = credentials.getAddress();
            log.info("New address: " + address);
            // Fetch private and public key
            String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
            String publicKey = credentials.getAddress();
            log.info("Private Key: " + privateKey);
            log.info("Public Key: " + publicKey);
            return credentials;
        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new RuntimeException();
        }

    }
    @SneakyThrows
    public void createEthTransaction(){
        try {
            // Load sender credentials
            Credentials credentials = WalletUtils.loadCredentials("sender_password", "path_to_sender_wallet_file");
            // Send coins
            TransactionReceipt receipt = Transfer.sendFunds(
                    web3j, credentials, "recipient_address",
                    BigDecimal.valueOf(0.01), Convert.Unit.ETHER).send();

            // System out transaction hash
            System.out.println("Transaction complete: " + receipt.getTransactionHash());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @SneakyThrows
    public EthBlockNumber getBlockNumber() {
        try {
            return this.web3j.ethBlockNumber()
                    .send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @SneakyThrows
    public EthBlock getLastBlock() {
        try {
            return this.web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public EthAccounts getEthAccounts() {
        try {
            EthAccounts result;
            result = this.web3j.ethAccounts()
                    .send();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @SneakyThrows
    public EthGetTransactionCount getTransactionCount(String address) {
        try {

            return this.web3j.ethGetTransactionCount(address,
                            DefaultBlockParameter.valueOf("latest")).send();

        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new RuntimeException();
        }
    }
    @SneakyThrows
    public EthGetBalance getEthBalance(String address) {
        try {
            EthGetBalance balance = this.web3j.ethGetBalance(address,
                            DefaultBlockParameter.valueOf("latest"))
                    .send();
            log.info(balance.getResult());
        log.info(balance.getBalance().toString());
        return balance;
        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new RuntimeException("Error while getting eth balance ");
        }
    }
}
