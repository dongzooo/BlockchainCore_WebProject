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

import com.block.project.dto.MemberDTO;
import com.block.project.dto.PageRequestDTO;
import com.block.project.dto.ProductDTO;
import com.block.project.dto.TradeDTO;
import com.block.project.dto.WalletDTO;
import com.block.project.model.Member;
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
	   //메인화면
		@GetMapping({"/","/main"})
		public String productList(PageRequestDTO pageRequestDTO, Model model) {
			log.info("메인화면 이동");
			
//			System.out.println("1111111111111"+productService.getList(pageRequestDTO));
			model.addAttribute("presult", productService.getList(pageRequestDTO));
			
			return "/main";
		}
		
	   //회사소개 
		@GetMapping({"/about"})
		public void about( Model model) {
			log.info("회사소개 화면 이동");
		}
			
		//구매 설정 
		@GetMapping({"/productBuy"})
		public void productBuy(ProductDTO productDTO, MemberDTO memberDTO ,Model model) {
			log.info("구매확인으로 이동");
			//상품샘플
			productDTO = ProductDTO.builder()
			.pnum(1l)
			.name("갤럭시 21")
			.price(30000)
			.thumb("/image/2.jpg")
			.detail("미개봉")
			.achieved(0)
			.nickname("멤버1")
			.build();
			
			//지갑생성
			WalletDTO walletDTO = WalletDTO.builder()
					.wnum(1L)
					.walletid("x80dfsd314")
					.balance(140000)
					.build();			
			
			memberDTO= MemberDTO.builder()
                    .wnum(walletDTO.getWnum())
                    .mid("dhel48")
                    .pw("0000")
                    .nickname("user")
                    .tel("010-234-1234")
                    .addr("서울시 서대문구 조은동")
                    .star(3f)
                    .alias("중고사랑")
                    .build();
			
			//멤버샘플
			model.addAttribute("presult", productDTO);
			model.addAttribute("mresult", memberDTO );
			model.addAttribute("wresult", walletDTO );
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
