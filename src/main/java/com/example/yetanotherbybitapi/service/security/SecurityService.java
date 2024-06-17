package com.example.yetanotherbybitapi.service.security;

import com.example.yetanotherbybitapi.dto.UserCreateEditDto;
import com.example.yetanotherbybitapi.model.MyUser;
import com.example.yetanotherbybitapi.repository.UserRepository;
import com.example.yetanotherbybitapi.service.web3j.Web3JService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Credentials;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Web3JService web3JService;
    @Transactional
    public String registration(UserCreateEditDto userCreateEditDto){
        Optional<MyUser> byUsername = userRepository.findByUsername(userCreateEditDto.getUsername());
        if (byUsername.isPresent()){
            throw new RuntimeException("Username already exists");
        }
        Credentials credentials = web3JService.createNewEthAddress(userCreateEditDto.getPassword());
        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
        String publicKey = credentials.getAddress();
        MyUser user = MyUser.builder()
                .username(userCreateEditDto.getUsername())
                .password(passwordEncoder.encode(userCreateEditDto.getPassword()))
                .email(userCreateEditDto.getEmail())
                .privateKeyEth(privateKey)
                .publicKeyEth(publicKey)
                .build();
        userRepository.save(user);
        log.info("User {} successfully registered", userCreateEditDto.getUsername());
        return "Спасибо за регистрацию! Ваш eth адрес для пополнения: " + publicKey;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        };
        return userRepository.findByUsername(username)
                .map(user -> new User(user.getUsername(), user.getPassword(), Collections.singleton(grantedAuthority)))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Failed to retrieve user: %s", username)));
    }
}
