package com.block.project.service;

import java.util.List;

import com.block.project.dto.TradeDTO;
import com.block.project.model.Product;
import com.block.project.model.Trade;



public interface TradeService {
	//거래내역 생성
	public Long createTrade(TradeDTO dto);
	
	public void buyConfirm(TradeDTO dto);
	
	//DTO를 Entity로 변환해주는 메서드
	default Trade dtoToEntity(TradeDTO dto) {
		//프론트 완성시 productDTO를 받아서 아래 변수에 대입한다.				
		Product product = Product.builder().pnum(1L).build();
//		Product product = Product.builder().pNum()
				
		
		Trade trade = Trade.builder().bWallet(dto.getBWallet())
				.sWallet(dto.getSWallet()).date(dto.getDate()).product(product)
				.txid(dto.getTxid()).total(dto.getTotal()).confirm(dto.getConfirm()).build();
		
		
		return trade;
	}
	
	//Entity를 DTO로 변환해주는 메서드
	public static TradeDTO entityToDTO(Product product, Trade trade) {
		TradeDTO tradeDTO = TradeDTO.builder()
				.tNum(trade.getTNum()).bWallet(trade.getBWallet())
				.sWallet(trade.getSWallet()).date(trade.getDate())
				.pNum(trade.getProduct().getPnum()).txid(trade.getTxid()).total(trade.getTotal())
				.confirm(trade.getConfirm())
				.build();
				
		
		return tradeDTO;
	}

	
}
