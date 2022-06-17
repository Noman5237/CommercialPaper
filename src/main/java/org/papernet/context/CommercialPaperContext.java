package org.papernet.context;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.papernet.util.Repository;
import org.papernet.model.CommercialPaper;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class CommercialPaperContext extends Context implements Repository<CommercialPaper> {
	
	public CommercialPaperContext(ChaincodeStub stub) {
		super(stub);
	}
	
	@Override
	public CommercialPaper save(CommercialPaper commercialPaper) {
		stub.putState(commercialPaper.getKey(),
		              commercialPaper.toJSON()
		                             .getBytes(StandardCharsets.UTF_8));
		
		return commercialPaper;
	}
	
	@Override
	public Optional<CommercialPaper> findByKey(String key) {
		var data = stub.getState(key);
		
		if (data != null) {
			return Optional.of(CommercialPaper.fromJSON(new String(data)));
		}
		
		return Optional.empty();
	}
}
