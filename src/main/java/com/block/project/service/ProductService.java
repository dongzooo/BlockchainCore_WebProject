package com.block.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.block.project.dto.PageRequestDTO;
import com.block.project.dto.PageResponseDTO;
import com.block.project.dto.ProductDTO;
import com.block.project.dto.TradeDTO;
import com.block.project.model.Member;
import com.block.project.model.Product;
import com.block.project.repository.MemberRepository;


public interface ProductService {


	//데이터 삽입
	public Long registerProduct(ProductDTO dto);
	public ProductDTO getProduct(ProductDTO dto);
	public PageResponseDTO<ProductDTO, Product> getList(PageRequestDTO pageRequestDTO);
	public Long updateProduct(ProductDTO dto);
	public String deleteProduct(ProductDTO dto);
//	public String getProduct(ProductDTO dto);
	
	public default Product dtoToEntityProduct(ProductDTO dto) {
		
		Product p = Product.builder()
				.member(Member.builder().mnum(41L).build())
				.name(dto.getName())
				.price(dto.getPrice())
				.thumb(dto.getThumb())
				.detail(dto.getDetail())
				.achieved(dto.getAchieved())
				.build();
		return p;
	}
	public default ProductDTO entityToDto(Product p) {
		ProductDTO dto = ProductDTO.builder()
				.pnum(p.getPnum())
				.name(p.getName())
				.price(p.getPrice())
				.thumb(p.getThumb())
				.detail(p.getDetail())
				.achieved(p.getAchieved())
				.nickname(p.getMember().getNickname())
				.build();
		return dto;
	}
	
}
