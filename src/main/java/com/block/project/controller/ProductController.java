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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.block.project.dto.PageRequestDTO;
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
public class ProductController {
	
	private final ProductService productService;
	
	   //메인함수
		@GetMapping({"/","/main"})
		public String productList(PageRequestDTO pageRequestDTO, Model model) {
			log.info("거래저장으로 이동");
			
			model.addAttribute("result", productService.getList(pageRequestDTO));
			
			return "/main";
		}		
		
//		@GetMapping("/list")
//		public void list(PageRequestDTO pageRequestDTO, Model model) {
//			log.info("목록보기");
//			model.addAttribute("result", productService.getList(pageRequestDTO));
//		};
		

		
		

		
	
	

}
