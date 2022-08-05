package com.block.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_img_file")
@ToString(exclude = {"product"})
public class ProductImgFile {		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imgnum")
    private Long imgNum;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pnum", nullable=false)
    private Product product;
	
    @Column(name = "imgname", nullable=false)
    private String imgName;
    
    @Column(name = "imgtype", nullable=false)
    private String imgType;
    
    @Column(name = "imgurl", nullable=false)
    private String imgUrl;
}
