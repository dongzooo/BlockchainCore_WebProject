package com.block.project.controller;
import java.util.Date;
//http://localhost/board/trade
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.block.project.dto.PageRequestDTO;
import com.block.project.dto.ProductDTO;
import com.block.project.dto.TradeDTO;
import com.block.project.model.Trade;
import com.block.project.service.ProductService;
import com.block.project.service.TradeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//데이터를 리턴하기 위한 COntroller를 만들기 위한 어노테이션
@Controller
@Log4j2
@RequiredArgsConstructor
//@RequestMapping("popmarket")
public class ProductController {
	
	private final ProductService productService;
	
	   //메인함수
		@GetMapping({"/","/main"})
		public String productList(PageRequestDTO pageRequestDTO, Model model) {
			log.info("메인화면 이동");
			
			model.addAttribute("result", productService.getList(pageRequestDTO));
			
			return "/main";
		}
		
		@GetMapping({"/productUpload"})
		public void productUpload() {
			log.info("상품저장으로 이동");
			
		}
		
		@PostMapping("/productUpload")
		public String createTrade(ProductDTO dto,RedirectAttributes redirectAttributes) {
			log.info("거래 저장 중.." + dto);
//			Date date = new Date();
			dto.setAchieved(0);
//			dto.setDate(date);
			Long tNum = productService.registerProduct(dto);
			//View에 데이터 전달
			redirectAttributes.addFlashAttribute("msg", tNum + " 삽입");
			
			return "redirect:/productUpload";
		}
		
		



		
		
//		@GetMapping("/list")
//		public void list(PageRequestDTO pageRequestDTO, Model model) {
//			log.info("목록보기");
//			model.addAttribute("result", productService.getList(pageRequestDTO));
//		};
		

		
		

		
	
	

}
