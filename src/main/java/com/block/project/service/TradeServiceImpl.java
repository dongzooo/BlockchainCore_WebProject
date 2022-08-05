package com.block.project.service;


import org.springframework.stereotype.Service;

import com.block.project.dto.TradeDTO;
import com.block.project.model.Trade;
import com.block.project.repository.TradeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
	
	private void validate(final Trade entity) {
		if(entity == null) {
			log.warn("Entity is Null");
			throw new RuntimeException("Entity cannot be null");
		}
		if(entity.getTNum()==null) {
			log.warn("TNum is Null");
			throw new RuntimeException("Unknown user");
		}
	}
	
	private final TradeRepository tradeRepository;

	@Override
	public Long createTrade(TradeDTO dto) {
		Trade trade = dtoToEntity(dto);
		//entity 유효성 검사
		validate(trade);
		//DB저장
		tradeRepository.save(trade);
		return trade.getTNum();
	}



}
