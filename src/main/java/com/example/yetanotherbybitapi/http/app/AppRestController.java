package com.example.yetanotherbybitapi.http.app;

import com.example.yetanotherbybitapi.dto.UserCreateEditDto;
import com.example.yetanotherbybitapi.service.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v5/")
@RequiredArgsConstructor
public class AppRestController {
    private final SecurityService securityService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registration(@RequestBody UserCreateEditDto userCreateEditDto){
        return new ResponseEntity<>(securityService.registration(userCreateEditDto), HttpStatus.CREATED);
    }
}
