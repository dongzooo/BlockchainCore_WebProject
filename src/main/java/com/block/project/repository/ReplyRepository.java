package com.block.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.block.project.model.Product;
import com.block.project.model.Reply;



public interface ReplyRepository extends JpaRepository<Reply, Long> {
	//Member를 이용해서 Member 가 작성한 모든 Item을 조회하는 메서드
	List<Reply> findReplyByProduct(Product product);
}
