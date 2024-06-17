package com.example.yetanotherbybitapi.repository;

import com.example.yetanotherbybitapi.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findByUsername(String username);
}
