package com.block.project.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "member")
@ToString(exclude = {"wallet"})
public class Member extends BaseEntity{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mnum;
   
    @OneToOne//무조건 lazy로
    @JoinColumn(name = "walletid", nullable=false)
    private Wallet wallet;
    
    private String id;
    private String pw;
    private String nickname;
    private String tel;
    private String addr;
    private Float star;  
}