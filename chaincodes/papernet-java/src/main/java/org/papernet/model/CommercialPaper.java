package org.papernet.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import org.papernet.util.State;

import java.util.Date;

@Data
@DataType
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommercialPaper implements State {
	
	@Property
	private String issuer;
	
	@Property
	private String paperNo;
	
	@Property
	private CommercialPaperState state;
	
	@Property
	private int faceValue;
	
	@Property
	private Date issueTime;
	
	@Property
	private Date maturityTime;
	
	@Property
	private String owner;
	
	@SneakyThrows
	public static CommercialPaper fromJSON(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, CommercialPaper.class);
	}
	
	@SneakyThrows
	public String toJSON() {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(this);
	}
	
	@JsonIgnore
	public String getKey() {
		return String.format("%s:%s:%s", getClass().getName(), issuer, paperNo);
	}
	
}
