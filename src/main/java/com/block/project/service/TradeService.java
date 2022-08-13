package com.block.project.service;

import java.time.LocalDateTime;
import java.util.List;

import com.block.project.dto.GetBlkDTO;
import com.block.project.dto.PageRequestDTO;
import com.block.project.dto.PageResponseDTO;
import com.block.project.dto.RespMkBlkDTO;
import com.block.project.dto.TradeDTO;
import com.block.project.model.Product;
import com.block.project.model.Trade;



public interface TradeService {
	//Trade 거래내역 생성
	public Long createTrade(TradeDTO dto);
	//구매확정 버튼 클릭시 블록생성
	public RespMkBlkDTO buyConfirm(TradeDTO dto);
	//구매내역 블러오기
	PageResponseDTO<TradeDTO, Trade> getList(PageRequestDTO requestDTO);
	//블록조회
	GetBlkDTO getBlock(String txid);
	
	//DTO를 Entity로 변환해주는 메서드
	default Trade dtoToEntity(TradeDTO dto) {
		//프론트 완성시 productDTO를 받아서 아래 변수에 대입한다.				
		Product product = Product.builder().pnum(dto.getPnum()).build();
//		Product product = Product.builder().pNum()
				
		
		Trade trade = Trade.builder().bwallet(dto.getBwallet())
				.swallet(dto.getSwallet()).date(LocalDateTime.now()).product(product)
				.txid(dto.getTxid()).total(dto.getTotal()).confirm(dto.getConfirm()).build();
		
		
		return trade;
	}
	
	
	//Entity를 DTO로 변환해주는 메서드
	default TradeDTO entityToDTO( Trade trade) {
		TradeDTO tradeDTO = TradeDTO.builder()
				.tnum(trade.getTnum())
				.bwallet(trade.getBwallet())
				.swallet(trade.getSwallet()).date(trade.getDate())
				.pnum(trade.getProduct().getPnum()).txid(trade.getTxid()).total(trade.getTotal())
				.confirm(trade.getConfirm())
				.build();
				
		
		return tradeDTO;
	}
	default TradeDTO tradeToTradeDTO(Trade trade) {
	      TradeDTO dto = TradeDTO.builder()
	            .tnum(trade.getTnum())
	            .bwallet(trade.getBwallet())
	            .swallet(trade.getSwallet())
	            .txid(trade.getTxid())
	            .total(trade.getTotal())
	            .confirm(trade.getConfirm())
	            .pnum(trade.getProduct().getPnum())
	            .date(trade.getDate())
	            .build();
	      return dto ;
	   }

	
	
}
