package org.papernet.service;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.shim.ChaincodeStub;

@Contract (name = "org.papernet.commercialpaper")
public class CommercialPaperContract implements ContractInterface {
	
	@Override
	public Context createContext(ChaincodeStub stub) {
		return ContractInterface.super.createContext(stub);
	}
}
