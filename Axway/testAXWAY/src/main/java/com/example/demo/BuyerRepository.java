package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface BuyerRepository<T extends Buyer,Long > extends JpaRepository<T, Long> {}



