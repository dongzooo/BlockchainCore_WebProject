package com.block.project.controller;
import java.util.Date;
//http://localhost/board/trade
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.block.project.dto.TradeDTO;
import com.block.project.model.Trade;
import com.block.project.service.TradeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//데이터를 리턴하기 위한 COntroller를 만들기 위한 어노테이션
@Controller
@Log4j2
@RequiredArgsConstructor
public class TradeController {
	
	private final TradeService tradeService;
	
	   //구매버튼 클릭 시 거래를 DB에 저정 처리할 메서드
		@GetMapping("/board/trade")
//		@GetMapping("/main")
		public void createTrade() {
			log.info("거래저장으로 이동");
		}		
		

		@PostMapping("/board/trade")
		public String createTrade(TradeDTO dto, RedirectAttributes redirectAttributes) {
			log.info("거래 저장 중.." + dto);
			Date date = new Date();
//			dto.setTNum(dto.getTNum());
			dto.setConfirm(0);
			dto.setDate(date);
			Long tNum = tradeService.createTrade(dto);
			//View에 데이터 전달
			redirectAttributes.addFlashAttribute("msg", tNum + " 삽입");
			
			return "redirect:/board/trade";
		}
		
		
		

		
	
	

}
