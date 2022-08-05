package com.block.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.block.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
//	@Query(value="select b, w,count(r) "
//			+ "from Board b left join b.member w left join Reply r On r.board = b "
//			+ "group by b",
//		   countQuery="select count(b) from Board b")
//	Page<Object []> getProductWithReplyCount(Pageable Pageable);

}
