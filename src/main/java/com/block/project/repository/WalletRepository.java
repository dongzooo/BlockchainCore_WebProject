package com.block.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.block.project.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, String>{
	
}
