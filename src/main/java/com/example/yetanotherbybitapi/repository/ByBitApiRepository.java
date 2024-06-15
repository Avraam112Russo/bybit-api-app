package com.example.yetanotherbybitapi.repository;


import com.example.yetanotherbybitapi.model.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ByBitApiRepository extends JpaRepository<OrderDTO, Integer> {

}
