package com.block.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RespMkBlkDTO {
	@JsonProperty("Hash")
	private String Hash;
	@JsonProperty("Txid")
	private String Txid;
}
