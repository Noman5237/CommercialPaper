package org.papernet.service;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.papernet.context.CommercialPaperContext;

import java.util.logging.Logger;

@Contract (name = "org.papernet.commercialpaper")
public class CommercialPaperContract implements ContractInterface {
	
	private final static Logger LOGGER = Logger.getLogger(CommercialPaperContract.class.getName());
	
	@Override
	public Context createContext(ChaincodeStub stub) {
		return new CommercialPaperContext(stub);
	}
	
	
}
