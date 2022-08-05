//package com.block.project.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Getter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "wallet")
//public class Wallet {
//    @Id
//    @Column(name = "walletid")
//    private String walletId;
//    
//    @Column(name = "balance")
//    private Integer balance;
//}
//
//
//package com.block.project.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "member")
//public class Member {
//    @Id
//    @Column(name = "mnum")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long mNum;
//
//    @OneToOne
//    @JoinColumn(name = "walletid")
//    private Wallet wallet;
//
//    @Column(name = "id", nullable=false)
//    private String id;
//
//    @Column(name = "pw", nullable=false)
//    private String pw;
//
//    @Column(name = "nickname", nullable=false)
//    private String nickName;
//
//    @Column(name ="tel", nullable=false)
//    private String tel;
//
//    @Column(name = "addr", nullable=false)
//    private String addr;
//
//    @Column(name = "star")
//    private Double star;
//}
//
//package com.block.project.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "member_auth")
//public class MemberAuth {
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "mnum")
//    private Member member;
//
//    @Column(name="auth", nullable=false)
//    private String auth;
//}
//
//
//
