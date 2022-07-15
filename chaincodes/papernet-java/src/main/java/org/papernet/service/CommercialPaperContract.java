package org.papernet.service;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.papernet.context.CommercialPaperContext;
import org.papernet.model.CommercialPaper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

import static org.papernet.model.CommercialPaperState.*;

@Contract (name = "org.papernet.commercialpaper")
public class CommercialPaperContract implements ContractInterface {
	
	private final static Logger LOGGER = Logger.getLogger(CommercialPaperContract.class.getName());
	
	@Override
	public Context createContext(ChaincodeStub stub) {
		return new CommercialPaperContext(stub);
	}
	
	private CommercialPaper getCommercialPaper(CommercialPaperContext context, String issuer, String paperNo) {
		String key = CommercialPaper.builder()
		                            .issuer(issuer)
		                            .paperNo(paperNo)
		                            .build()
		                            .getKey();
		
		Optional<CommercialPaper> optionalCommercialPaper = context.findByKey(key);
		
		if (optionalCommercialPaper.isEmpty()) {
			throw new RuntimeException(String.format("paper with issuer: %s and no: %s is not found", issuer, paperNo));
		}
		
		return optionalCommercialPaper.get();
	}
	
	@Transaction
	public CommercialPaper issue(CommercialPaperContext context,
	                             String issuer,
	                             String paperNo,
	                             String maturityTime,
	                             int faceValue) throws ParseException {
		
		CommercialPaper commercialPaper = new CommercialPaper(issuer,
		                                                      paperNo,
		                                                      ISSUED,
		                                                      faceValue,
		                                                      new Date(),
		                                                      new SimpleDateFormat("yyyy-MM-dd").parse(maturityTime),
		                                                      issuer);
		
		return context.save(commercialPaper);
	}
	
	@Transaction
	public CommercialPaper buy(CommercialPaperContext context, String issuer, String paperNo, String newOwner) {
		CommercialPaper commercialPaper = getCommercialPaper(context, issuer, paperNo);
		
		if (commercialPaper.getState() == ISSUED) {
			commercialPaper.setState(TRADING);
		}
		
		if (commercialPaper.getState() != TRADING) {
			throw new RuntimeException(String.format("Paper %s is not trading. Current state = %s",
			                                         commercialPaper.getKey(),
			                                         commercialPaper.getState()
			                                                        .name()));
		}
		
		commercialPaper.setOwner(newOwner);
		
		return context.save(commercialPaper);
	}
	
	
	@Transaction
	public CommercialPaper redeem(CommercialPaperContext context, String issuer, String paperNo) {
		CommercialPaper commercialPaper = getCommercialPaper(context, issuer, paperNo);
		
		if (commercialPaper.getState() == REDEEMED) {
			throw new RuntimeException(String.format("Paper %s is already redeemed.", commercialPaper.getKey()));
		}
		
		commercialPaper.setOwner(commercialPaper.getIssuer());
		commercialPaper.setState(REDEEMED);
		
		return context.save(commercialPaper);
	}
	
}
