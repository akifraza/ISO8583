package com.myapp.iso;

import org.jpos.iso.ISOException;
import org.junit.Test;


public class Loopers {
	
	
	
	@Test
	public void dotest() throws ISOException, InterruptedException {
		
		systemHelperTest test1 = new systemHelperTest();
		for (int a=0;a<2000;a++) {
			System.out.println ("Started....");
			test1.testGenerateInqReq();
		}
		
	}
	

}
