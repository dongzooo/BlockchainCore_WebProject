package com.block.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reply")
public class Reply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long rnum;
	
	private String reply;
	
	private String replyer;
	
	private Long grp;
	
	private Long lev;
	
	//Product Entity를 N:1 관계로 참조([table name]_[PK]컬럼 생성됨)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pnum")
	private Product product;
	
	
	
	

}
