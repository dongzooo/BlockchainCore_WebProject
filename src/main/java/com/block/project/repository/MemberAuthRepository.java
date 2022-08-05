package com.block.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.block.project.model.MemberAuth;

public interface MemberAuthRepository extends JpaRepository<MemberAuth, Long>{

}
