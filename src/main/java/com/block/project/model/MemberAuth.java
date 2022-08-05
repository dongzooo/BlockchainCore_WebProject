package com.block.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_auth")
@ToString(exclude = {"member"})
public class MemberAuth {
    @Id
    @Column(name = "manum")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mnum", nullable=false)
    private Member member;

    @Column(name="auth", nullable=false)
    private String auth;
}