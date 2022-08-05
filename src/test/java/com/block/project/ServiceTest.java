package com.block.project;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.block.project.dto.PageRequestDTO;
import com.block.project.dto.PageResponseDTO;
import com.block.project.dto.ProductDTO;
import com.block.project.model.Product;
import com.block.project.service.ProductService;


@SpringBootTest
public class ServiceTest {

	@Autowired
	private ProductService productService;
	
//	//상품등록
//	//@Test
//	public void testRegisterProduct() {
//		ProductDTO dto = ProductDTO.builder()
//				.name("DTO와서비스")
//				.price(1000)
//				.thumb("썸네일")
//				.detail("디테일")
//				.achieved(0)
//				.nickname("id3") //???????모르겠다 이거 
//				.build();
//		Long result = productService.registerProduct(dto);
//		System.out.println(result);
//	}
//	//하나조회
//	//@Test
//	@Transactional
//	@Commit
//	public void testGetProduct() {
//		System.out.println();
//		ProductDTO dto = ProductDTO.builder()
//				.pnum(1L)
//				.build();
//		System.out.println(dto);
//		System.out.println(productService.getProduct(dto));
//		
//	}
//	
	//전체조회
	@Test
	@Transactional
	@Commit
	public void testList() {
		System.out.println("************************!");
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		System.out.println("************************2"+pageRequestDTO);
		PageResponseDTO<ProductDTO, Product> resultDTO = productService.getList(pageRequestDTO);
		System.out.println("************************3");
		for(ProductDTO productDto : resultDTO.getDtoList()) {
			System.out.println(productDto);
		}
	}
	

}
