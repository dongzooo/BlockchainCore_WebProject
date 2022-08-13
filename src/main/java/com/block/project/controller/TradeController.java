package com.block.project.controller;
import java.time.LocalDateTime;
import java.util.Date;
//http://localhost/board/trade
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.block.project.dto.GetBlkDTO;
import com.block.project.dto.PageRequestDTO;
import com.block.project.dto.RespMkBlkDTO;
import com.block.project.dto.TradeDTO;
import com.block.project.model.Product;
import com.block.project.model.Trade;
import com.block.project.repository.TradeRepository;
import com.block.project.service.TradeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//데이터를 리턴하기 위한 COntroller를 만들기 위한 어노테이션
@Controller
@Log4j2
@RequiredArgsConstructor
public class TradeController {
	
	private final TradeService tradeService;
	private final TradeRepository tradeRepository;
	
	   //tradeComfir, test데이터 생성용 페이지
		@GetMapping("/myPage/trade")
//		@GetMapping("/main")
		public void createTrade() {
			log.info("거래저장으로 이동");
		}		
		
		//tradeComfir, test데이터 생성용 페이지
		@PostMapping("/myPage/trade")
		public String createTrade(TradeDTO dto,RedirectAttributes redirectAttributes) {
//			dto.setTNum(dto.getTNum());
			dto.setPnum(1L);
			dto.setConfirm(0);
			dto.setDate(LocalDateTime.now());
			log.info("거래 저장 중.." + dto);
			System.out.println("+++++++"+dto);
//			Long tNum = tradeService.buyConfirm(dto);
			tradeService.createTrade(dto);
			//View에 데이터 전달
			redirectAttributes.addFlashAttribute("msg 삽입");
			
			return "redirect:/myPage/trade";
		}
		
		
		
		
		   //구매확정버튼 클릭 시 거래를 DB에 저정 처리할 메서드
			@GetMapping("/myPage/tradeConfirm")
			public void createTradeConfirm(PageRequestDTO pageRequestDTO, Model model) {
				   log.info("목록 보기.......");	
				   				 
				   System.out.println("~~~~~~~~~~~~~~~~~~~"+tradeService.getList(pageRequestDTO));
				   model.addAttribute("result", tradeService.getList(pageRequestDTO));
				   
			}		
			

			
			@PostMapping("/myPage/tradeConfirm")
			public String createTradeConfirm(TradeDTO dto, HttpSession httpSession,RedirectAttributes redirectAttributes) {
				  
				   System.out.println("dto~~~~~~~~~~~~~~~~~~~~~~~"+dto); 
				   RespMkBlkDTO respDto = tradeService.buyConfirm(dto);
//				   System.out.println("dto22~~~~~~~~~~~~~~~~~~~~~"+respDto);
//				   log.info("넘어온 파라미터는 ?????");
				   //System.out.println("넘어온값은:" + dto);
				   //tradeService.comfirm();
				   
				   //업데이트분 tnum(PK)이 조건절이 되어서 모든 필드값을 업데이트해준다.
				   if(respDto != null) {
					   System.out.println("update 때렸어요");
					   Product product = Product.builder().pnum(dto.getPnum()).build();
					   Trade t = Trade.builder()
							   .tnum(dto.getTnum())
							   .bwallet(dto.getBwallet())
							   .swallet(dto.getSwallet())
							   .txid(respDto.getTxid())
							   .product(product)
							   .date(LocalDateTime.now())
							   .total(dto.getTotal())
							   .confirm(1)		//구매확정이기에 0 -> 1로 변경
							   .build();
					   tradeRepository.save(t);
				   }
				
				return "redirect:/myPage/tradeConfirm";
			}
			
			@GetMapping("/myPage/getBlock")
			@ResponseBody
			public GetBlkDTO getBlock(String txid, HttpSession httpSession,RedirectAttributes redirectAttributes) throws Exception {
				GetBlkDTO respDTO = tradeService.getBlock(txid);
//				respDTO.setTimestamp(respDTO.getTimestamp())
				
				return respDTO;
			}
			
}
