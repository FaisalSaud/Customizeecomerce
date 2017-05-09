package SupportClasses;

import java.util.Random;

public class RandUtil {
    public static String getRandomString(int bits) {
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        for (int i = 0; i < bits; i++) {
            sb.append((char) (r.nextInt(26) + 97));
        }
        return sb.toString();
    }
}
