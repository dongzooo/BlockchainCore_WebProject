package com.block.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.block.project.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findById(String id);
}
