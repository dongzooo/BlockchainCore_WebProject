package com.block.project;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.block.project.dto.GetBlkDTO;
import com.block.project.dto.PageRequestDTO;
import com.block.project.dto.PageResponseDTO;
import com.block.project.dto.ProductDTO;
import com.block.project.dto.TradeDTO;
import com.block.project.model.Product;
import com.block.project.service.ProductService;
import com.block.project.service.TradeService;


@SpringBootTest
public class ServiceTest {

	@Autowired
	private ProductService productService;
	@Autowired
	private TradeService tradeService;
	
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
//	@Test
//	@Transactional
//	@Commit
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
	
//	@Test
//	@Transactional
	public void getBlock() {

	            
		GetBlkDTO respDTO = tradeService.getBlock("d2b7370c8b8f1bfe1b4fbf7fcd53e6547b10924b7d9135df6c0c3e79eebef8a2");
//System.out.println("************888"+respDTO);
		
	}
	@Test
	@Transactional
	public void setTiemstamp() {
		//MjAyMi0wOC0xMiAxMTozNToyNy40MjUwMjEyICswOTAwIEtTVA
		String time = "MjAyMi0wOC0xMiAxMTozNToyNy40MjUwMjEyICswOTAwIEtTVA";
		//System.out.println("************888"+respDTO);
		
	}
	

}
