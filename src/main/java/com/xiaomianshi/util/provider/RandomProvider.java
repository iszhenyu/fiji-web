package com.xiaomianshi.util.provider;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author zhen.yu
 * @since 2017/6/23
 */
public class RandomProvider {

    private static final Random random;

    static {
        random = initializeRandom();
    }

    public static Random getRandom() {
        return random;
    }

    private static Random initializeRandom() {
        try {
            SecureRandom ran = SecureRandom.getInstance("SHA1PRNG");
            byte[] seed = ran.generateSeed(20);
            return new SecureRandom(seed);
        } catch (Exception ex) {
            return new Random();
        }
    }
}
