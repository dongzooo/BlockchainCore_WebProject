package com.block.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.block.project.repository.MemberAuthRepository;
//import com.block.project.repository.MemberAuthRepository;
import com.block.project.repository.MemberRepository;
import com.block.project.repository.ProductImgFileRepository;
import com.block.project.repository.ProductRepository;
import com.block.project.repository.ReplyRepository;
import com.block.project.repository.TradeRepository;
import com.block.project.repository.WalletRepository;
import com.block.project.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.block.project.dto.PageRequestDTO;
import com.block.project.dto.PageResponseDTO;
import com.block.project.dto.ProductDTO;
import com.block.project.dto.TradeDTO;
import com.block.project.model.Member;
import com.block.project.model.MemberAuth;
import com.block.project.model.Product;
import com.block.project.model.ProductImgFile;
import com.block.project.model.Reply;
import com.block.project.model.Trade;
//import com.block.project.model.MemberAuth;
import com.block.project.model.Wallet;

@SpringBootTest
public class RepositoryTest {
	
	
	//Wqllet********
	@Autowired
	private WalletRepository walletRepository;
	
//	@Test
//	@Transactional
//	@Commit
    public void insertWallet() {		
    	int balanceTmp = (int)(Math.random() *10000);
        IntStream.rangeClosed(1,5).forEach(i -> {
            Wallet wallet = Wallet.builder()
                    .walletid("w"+i+i*2 +"afnjd2mcdk42")
                    .balance(balanceTmp)
                    .build();
            walletRepository.save(wallet);
        });
    }
    	
	//Members, wallet & Auth ??????********
	@Autowired
	private MemberRepository memberRepository;
//	private MemberAuthRepository memberAuthRepository;
	
	//??????????????? ????????????
    public Wallet newWallet() {
		int balanceTmp = (int)(Math.random() *10000);
		String id = "w"+(int)balanceTmp/10 +"afnjd2mcdk42";
		
        Wallet wallet = Wallet.builder()
                    .walletid(id)
                    .balance(balanceTmp)
                    .build();
        walletRepository.save(wallet);
        
        return wallet;
    }
    
    @Autowired
	private MemberAuthRepository memberAuthRepository;
	
	//??????????????? ??????????????? ??????
    
//	@Test
//	@Transactional
//	@Commit
    public void newAuth(Member member) {
//    	Member member= Member.builder().mnum((long)41).build();
    	System.out.println("********************2");
		MemberAuth memberAuth = MemberAuth.builder()
				.member(member)
				.auth("ROLE_MEMBER")
				.build();
		memberAuthRepository.save(memberAuth);
        
    }

    
//	@Test
//	@Transactional
//	@Commit
    public void insertMember() {
            IntStream.rangeClosed(1,5).forEach(i -> {
            	Wallet wallet = newWallet();
                Member member = Member.builder()
                        .wallet(wallet)
                        .id("id"+i)
                        .pw("0000")
                        .nickname("user")
                        .tel("010-"+i+"234-1234")
                        .addr("????????? ???????????? ??????"+(int)(Math.random() *10)+"e???")
                        .star(Float.valueOf(i%5))
                        .build();
                memberRepository.save(member);
                newAuth(member);
            });
    }
	
//    @Test
    public void jsonTransfer() {
    	 Map<String, Object> map = new HashMap<>();

         map.put("key1", "value1");
         map.put("key2", "value2");
  
        JSONObject json =  new JSONObject(map);
         System.out.printf( "JSON: %s", json);
    }
    
    
    
  //Product********
  	@Autowired
  	private ProductRepository productRepository;
  	
//  	@Test
//  	@Transactional
//  	@Commit
    public void insertProduct() {
 		Member member= Member.builder().mnum((long)41).build(); 
 		Product product = Product.builder()
           		.member(member)
                .name("?????? ??????")
                .price(1200000)
                .thumb("/image/1.jpg")
                .detail("2022?????? ?????? ???")
                .achieved(0)
                .build();
        productRepository.save(product);
        
        Member member2= Member.builder().mnum((long)42).build(); 
 		Product product2 = Product.builder()
           		.member(member2)
                .name("????????? 21")
                .price(50000)
                .thumb("/image/2.jpg")
                .detail("3??? ??????")
                .achieved(0)
                .build();
        productRepository.save(product2);
        
        Member member3= Member.builder().mnum((long)43).build(); 
 		Product product3 = Product.builder()
           		.member(member3)
                .name("???????????? ?????????2")
                .price(200000)
                .thumb("/image/3.jpg")
                .detail("????????? ??????")
                .achieved(0)
                .build();
        productRepository.save(product3);
//        IntStream.rangeClosed(1,40).forEach(i -> {
//          	int prices = (int)(Math.random() *10000);
//              Product product = Product.builder()
//               		.member(member)
//                    .name("?????? ?????????"+(int)prices/100)
//                    .price(prices)
//                    .thumb("?????????")
//                    .detail("?????????"+(int)prices/100+" ?????????")
//                    .achieved(0)
//                    .build();
//            productRepository.save(product);
//        });
      }
    
    //product_img_file********
    @Autowired
    private ProductImgFileRepository productImgFileRepository;
    
    private String imageType;
    
//	@Test
//	@Transactional
//	@Commit
	public void insertProductImgFile() {
		Product product= Product.builder().pnum((long)89).build(); 
	    IntStream.rangeClosed(1,5).forEach(i -> {
	    	imageType = "detail";
	    	if(i==1) {
	    		imageType = "thumb";
	    	}
	      	ProductImgFile productImgFile = ProductImgFile.builder()
	      			.product(product)
	      			.imgName("??????"+i)
	      			.imgType(imageType)
	      			.imgUrl("x"+i+"ecrwew")
	      			.build();
	        productImgFileRepository.save(productImgFile);
	    });
	  }
	
	
	
	//trade********
    @Autowired
    private TradeRepository tradeRepository ;
//    
//	@Test
//	@Transactional
//	@Commit
	public void insertTrade() {
		int price = (int)(Math.random() *100000);
		Product product= Product.builder().pnum((long)89).build(); 
//		Date date = new Date();
	    Trade trade = Trade.builder()
	    		.bwallet("w295afnjd2mcdk42")
	    		.swallet("w461afnjd2mcdk42")
	    		.date(LocalDateTime.now())
	    		.product(product)
	    		.txid(""+product.getPnum()*price)
	    		.total((long)price)
	    		.confirm(0)
	    		.build();
	    tradeRepository.save(trade);	    
	  }
	
	
	//Reply********
	
	@Autowired
	private ReplyRepository replyRepository;
//	   @Test
	   public void testInsertReply() {
	      
	      //FK ??????
	      Product prod = productRepository.findById(89L).get();   

	      for(Long i=(long) 0; i<15; i++) {
	         Reply reply = Reply.builder()
	               .product(prod)
	               .reply("reply test test"+(i+1))
	               .replyer((i)+"suyeon")
	               .grp(i)
	               .lev(i)
	               .rnum(i)
	               
	               .build();
	         replyRepository.save(reply);
	      }
	   
	   }
	      
	      //???????????? ??????
//	      @Test
	      public void getPaging() {
	         Sort sort=Sort.by("rnum").descending();
	         Pageable pageable=PageRequest.of(0, 10, sort);
	         Page<Reply> page=replyRepository.findAll(pageable);
	         //foreach??????????????? ????????? ????????? ??????, ???????????? ???????????? ??????
	         page.get().forEach(item->{
	            System.out.println(item);
	         });
	      }
	      
	      //?????????(pnum) ????????? ??????
	      //@Test
	      public void getFindReply() {
	         Product prod=Product.builder().pnum(11L).build();
	         List<Reply> list=replyRepository.findReplyByProduct(prod);
	         System.out.println(list);
	      }
	      
	      
	      //????????? ??????
	      //@Test
	      public void updateReply() {
	         Product prod=Product.builder().pnum(11L).build();
	         Reply item=Reply.builder().rnum(1L).grp(2L).lev(3L).reply("updateupdate").replyer("???")
	               .product(prod).build();
	         replyRepository.save(item);
	         
	      }
	      
	      //????????? ??????
	      //@Test
	      public void deleteReply() {
	         Reply item=Reply.builder().rnum(11L).build();
	         replyRepository.delete(item);
	      }
	      private ProductService productService;
	      

	      
	
	
}
