package com.block.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.block.project.dto.PageRequestDTO;
import com.block.project.dto.PageResponseDTO;
import com.block.project.dto.ProductDTO;
import com.block.project.dto.TradeDTO;
import com.block.project.model.Product;
import com.block.project.repository.MemberRepository;
import com.block.project.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final MemberRepository memberRepository;
	
	
	@Override
	public PageResponseDTO<ProductDTO, Product> getList(PageRequestDTO requestDTO) {
		Sort sort = Sort.by("pnum").descending();
		Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize(),sort);
		Page<Product> result = productRepository.findAll(pageable);
		
		Function<Product, ProductDTO> fn = (entity->entityToDto(entity));
		
		return new PageResponseDTO<>(result, fn);
	}


	@Override
	public Long registerProduct(ProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ProductDTO getProduct(ProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Long updateProduct(ProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String deleteProduct(ProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public String makeTx(TradeDTO tDto) {
//		// 성공하면 txid 반환
//		
//		return null;
//	}

}
