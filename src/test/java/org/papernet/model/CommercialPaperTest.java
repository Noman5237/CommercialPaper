package org.papernet.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.papernet.model.CommercialPaperState.ISSUED;

public class CommercialPaperTest {
	
	private static CommercialPaper commercialPaper;
	
	@BeforeEach
	void setUp() throws ParseException {
		commercialPaper = new CommercialPaper("John",
		                                      "0001",
		                                      ISSUED,
		                                      3000,
		                                      new Date(),
		                                      new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2024"),
		                                      "John");
	}
	
	@Test
	void toStringTest() {
		System.out.println(commercialPaper);
	}
	
	@Test
	void toJSON() {
		String serialized = commercialPaper.toJSON();
		System.out.println(serialized);
	}
	
	@Test
	void fromJSON() {
		CommercialPaper deserialized = CommercialPaper.fromJSON(commercialPaper.toJSON());
		System.out.println(deserialized);
		Assertions.assertEquals(deserialized, commercialPaper);
	}
	
	@Test
	void getKey() {
		CommercialPaper commercialPaper = CommercialPaper.builder()
		                                                 .issuer("John")
		                                                 .paperNo("0001")
		                                                 .build();
		System.out.println(commercialPaper.getKey());
	}
}
