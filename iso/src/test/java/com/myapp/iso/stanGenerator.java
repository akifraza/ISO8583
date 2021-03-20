package com.myapp.iso;

import org.junit.Test;


/**
 *
 * @author akifraza
 */
public class stanGenerator {
   
	@Test
    public void Generator() {
        RunningNumberGenerator gen = new RunningNumberGenerator();
        System.out.println("STAN : " + gen.generateStan());
        System.out.println("RRN : " + gen.generateRrn());
    }
    

}

