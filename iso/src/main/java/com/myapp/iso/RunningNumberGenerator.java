package com.myapp.iso;

import java.util.Random;

/**
 *
 * @author akifraza
 */
public class RunningNumberGenerator {
    public String generateStan() {
        Integer next = new Random().nextInt(999999);
        return String.format("%06d",next);
    }
        public String generateRrn() {
        Long next = new Random().nextLong();
        return String.valueOf(next);
    }
}

