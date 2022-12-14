package com.block.project.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.block.project.dto.GetBlkDTO;
import com.block.project.dto.MkBlkDTO;
import com.block.project.dto.PageRequestDTO;
import com.block.project.dto.PageResponseDTO;
import com.block.project.dto.RespMkBlkDTO;
import com.block.project.dto.TradeDTO;
import com.block.project.model.Trade;
import com.block.project.repository.TradeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
	
	
	//주입받기 위한 레포지토리
	//- 생성자에서 주입받으려면 final로 선언해야 함	
	private final TradeRepository tradeRepository;
	
	//Trade 거래내역 생성
	@Override
	public Long createTrade(TradeDTO dto) {
//		getEmployees();
		Trade trade = dtoToEntity(dto);
		//entity 유효성 검사
		//DB저장
		tradeRepository.save(trade);
		//블록생성
//		buyConfirm(dto);
		return trade.getTnum();
	}


	//구매내역 블러오기
	@Override
	public PageResponseDTO<TradeDTO, Trade> getList(PageRequestDTO requestDTO) {
		//글번호의 내림차순으로 목록정렬
//		Pageable pageable = requestDTO.getPageable(Sort.by("tnum").descending());
		Sort sort = Sort.by("tnum").descending();
		Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize(),sort);
		Page<Trade> result = tradeRepository.findAll(pageable);
		
		//Entity를 DTO로 변환해주는 함수 설정d
		Function<Trade, TradeDTO> fn =(entity->tradeToTradeDTO(entity));
		
		return new PageResponseDTO <>(result, fn);
	}

	
	//구매확정 버튼 클릭시 블록생성
	@Override
   public RespMkBlkDTO buyConfirm(TradeDTO dto) {
      // TODO Auto-generated method stub
      //URL 주소 
      String strUrl = "http://127.0.0.1:10000/MainBlockServer";
      //jsonMessage
      String jsonMessage = "";
      
      ObjectMapper mapper = new ObjectMapper();
//	      키            값
//	      From        => dto.getBwallet()
//	      To          => dto.getSwallet()
//	      Purpose    => "블록 생성"
//	      Amount     => dto.getTotal()
      // 이렇게 JSON으로 변경해서 보내주시면 될 것같아여 오른쪽에 있는 값으로여 ㅎㅎ
      
      MkBlkDTO jsonDto = new MkBlkDTO();
      jsonDto.setAmount(dto.getTotal());
      jsonDto.setFrom(dto.getBwallet());
      jsonDto.setTo(dto.getBwallet());
      jsonDto.setPurpose("블록 생성");
      try {
         jsonMessage = mapper.writeValueAsString(jsonDto); 
         // 이 부분 수정해야 될 것 같아여. 위에 처럼 dto에서 일부만 가져오는거라 통재로 보내주면 구조체랑
         // 매핑이 안되서 에러가 뜨거든용
         // 그말씀은 즉슨 to from amount만 여기서 보내라는말씀이시죠네. 거기에 Purpose    => "블록 생성" 이것도 넣어주셔야되용 설명 깔끔합니다.
         // 굿굿 :)
         // 다른 데는 고칠부분은 없는거 같아용 ㅎㅎ 너무너무 감사드리고요~ 이따가 또물어볼거생긱면 여쩌봐도될까여?^^ 당연하져 ㅎㅎ
         // 그럼 테스트할때 postman에서 restapi 통신 되는지 확인만 하면되겠네요 ? 넵 스프링에서도 버튼누르면 블럭 생성되게끔 구현하셨으니 버튼 눌러서 테스팅하는 것도 괜찮을거같아여
      } catch (JsonProcessingException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      
      try {         
         URL url = new URL(strUrl);         
         HttpURLConnection con = (HttpURLConnection) url.openConnection();         
         con.setConnectTimeout(5000);                                     		// 서버에 연결되는 Timeout 시간 설정         
         con.setReadTimeout(5000);                                        		// InputStream 읽어 오는 Timeout 시간 설정    
         con.setRequestMethod("POST");                                     		// json으로 message를 전달하고자 할 때          
         con.setRequestProperty("Content-Type", "application/json");         
         con.setDoInput(true);                                          		// Server 통신에서 입력 가능한 상태로 만듬
         con.setDoOutput(true);                                           		// Server 통신에서 입력 가능한 상태로 만듬(POST 데이터를 OutputStream으로 넘겨 주겠다는 설정)
         con.setUseCaches(false);         
         con.setDefaultUseCaches(false);
         OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
         wr.write(jsonMessage);                          
         wr.flush();
         StringBuilder sb = new StringBuilder();
         if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            //Stream을 처리해줘야 하는 귀찮음이 있음.
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
               sb.append(line).append("\n");
            }
            br.close();
            RespMkBlkDTO respDto = mapper.readValue(sb.toString(), RespMkBlkDTO.class);
            return respDto;
         }else{
            System.out.println(con.getResponseMessage());
         }
         
      }catch(Exception e){
         System.err.println(e.toString());
      }
	return null;
	}
	
	
	
	//블록조회
	@Override
	   public GetBlkDTO getBlock(String txid) {
	      // TODO Auto-generated method stub
	      //URL 주소 
	      String strUrl = "http://127.0.0.1:10000/MainBlockServer";
	      //jsonMessage
	      String jsonMessage = "";
	      
	      ObjectMapper mapper = new ObjectMapper();
//		      키            값
//		      From        => dto.getBwallet()
//		      To          => dto.getSwallet()
//		      Purpose    => "블록 생성"
//		      Amount     => dto.getTotal()
	      // 이렇게 JSON으로 변경해서 보내주시면 될 것같아여 오른쪽에 있는 값으로여 ㅎㅎ
	      
	      Map<String, Object> jsonInfo = new HashMap<>();

	      jsonInfo.put("TxidByHex", txid);
	      jsonInfo.put("Purpose", "블록 조회");
	      System.out.println(jsonInfo);

	      try {
	         jsonMessage = mapper.writeValueAsString(jsonInfo); 
	      } catch (JsonProcessingException e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	      }
	      
	      try {         
	         URL url = new URL(strUrl);         
	         HttpURLConnection con = (HttpURLConnection) url.openConnection();         
	         con.setConnectTimeout(5000);                                     		// 서버에 연결되는 Timeout 시간 설정         
	         con.setReadTimeout(5000);                                        		// InputStream 읽어 오는 Timeout 시간 설정    
	         con.setRequestMethod("POST");                                     		// json으로 message를 전달하고자 할 때          
	         con.setRequestProperty("Content-Type", "application/json");         
	         con.setDoInput(true);                                          		// Server 통신에서 입력 가능한 상태로 만듬
	         con.setDoOutput(true);                                           		// Server 통신에서 입력 가능한 상태로 만듬(POST 데이터를 OutputStream으로 넘겨 주겠다는 설정)
	         con.setUseCaches(false);         
	         con.setDefaultUseCaches(false);
	         OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
	         wr.write(jsonMessage);                          
	         wr.flush();
	         StringBuilder sb = new StringBuilder();
	         if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
	            //Stream을 처리해줘야 하는 귀찮음이 있음.
	            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
	            String line;
	            while ((line = br.readLine()) != null) {
	               sb.append(line).append("\n");
	            }
	            br.close();
	            GetBlkDTO respDto = mapper.readValue(sb.toString(), GetBlkDTO.class);
	            System.out.println("------------"+respDto);
	            return respDto;
	         }else{
	            System.out.println(con.getResponseMessage());
	         }
	         
	      }catch(Exception e){
	         System.err.println(e.toString());
	      }
		return null;
	   }
	
	



}
