package com.block.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.block.project.model.Member;
import com.block.project.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long>{

}
