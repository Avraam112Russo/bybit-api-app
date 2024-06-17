package com.example.yetanotherbybitapi.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
@Slf4j
public class ByBitApiConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    @SneakyThrows
    public Web3j web3j(){
        Web3j web3j = Web3j.build(new HttpService(
                "https://mainnet.infura.io/v3/08cc405b7d454eb49acbaf3c76207da9"));
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        return web3j;
    }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(c -> c.disable())
                    .cors(c -> c.disable())
                    .authorizeHttpRequests(auth -> auth.requestMatchers("/api/v5/registration").permitAll())
                    .authorizeHttpRequests(auth-> auth.anyRequest().authenticated())
                    .httpBasic(Customizer.withDefaults());
            // ..
            return http.build();
        }
        @Bean
        public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        }
}
