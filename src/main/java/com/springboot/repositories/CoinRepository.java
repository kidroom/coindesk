package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.models.Coin;

public interface CoinRepository extends JpaRepository<Coin,Integer>{

}
