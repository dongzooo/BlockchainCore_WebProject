package com.block.project.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private Long pnum;
	private Long mnum;
	private String name;
	private Integer price;
	private String thumb;
	private MultipartFile img;
	private String detail;
	private Integer achieved; 
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	//작성자 정보
	private String nickname;
	private Float star;
	
	//댓글 수
	private int replyCount;

	
}

