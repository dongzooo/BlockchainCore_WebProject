package com.block.project.dto;

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
	private Long tNum;
	
    private String bWallet;
	
	private String sWallet;
	
//	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date date;
	
    private Product pNum;
    
    private String txid;
    
    private Long total;
    
    private Integer confirm;
    
//    //거래상품 번호
//    private Long productNum;
    
    
}
