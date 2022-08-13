package com.block.project.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.block.project.model.Product;
import com.block.project.model.Trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeDTO {
	private Long tnum;
	
    private String bwallet;
	
	private String swallet;
	
//	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDateTime date;
	
    private Long pnum;
    
    private String txid;
    
    private Long total;
    
    private Integer confirm;
    
//    //거래상품 번호
//    private Long productNum;
    
    
}
