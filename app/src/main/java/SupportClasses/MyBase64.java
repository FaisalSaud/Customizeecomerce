package SupportClasses;

public class MyBase64 {
    public static String encode(byte[] raw) {
        StringBuffer encoded = new StringBuffer();
        for (int i = 0; i < raw.length; i += 3) {
            encoded.append(encodeBlock(raw, i));
        }
        return encoded.toString();
    }

    protected static char[] encodeBlock(byte[] raw, int offset) {
        int end;
        int i;
        int block = 0;
        int slack = (raw.length - offset) - 1;
        if (slack >= 2) {
            end = 2;
        } else {
            end = slack;
        }
        for (i = 0; i <= end; i++) {
            int neuter = 0;
            byte b = raw[offset + i];
            if (b < (byte) 0) {
                neuter = b + 256;
            } else {
                byte neuter2 = b;
            }
            block += neuter << ((2 - i) * 8);
        }
        char[] base64 = new char[4];
        for (i = 0; i < 4; i++) {
            base64[i] = getChar((block >>> ((3 - i) * 6)) & 63);
        }
        if (slack < 1) {
            base64[2] = '=';
        }
        if (slack < 2) {
            base64[3] = '=';
        }
        return base64;
    }

    protected static char getChar(int sixBit) {
        if (sixBit >= 0 && sixBit <= 25) {
            return (char) (sixBit + 65);
        }
        if (sixBit >= 26 && sixBit <= 51) {
            return (char) ((sixBit - 26) + 97);
        }
        if (sixBit >= 52 && sixBit <= 61) {
            return (char) ((sixBit - 52) + 48);
        }
        if (sixBit == 62) {
            return '+';
        }
        if (sixBit == 63) {
            return '/';
        }
        return '?';
    }

    public static byte[] decode(String base64) {
        int i;
        int pad = 0;
        for (i = base64.length() - 1; base64.charAt(i) == '='; i--) {
            pad++;
        }
        byte[] raw = new byte[(((base64.length() * 6) / 8) - pad)];
        int rawIndex = 0;
        for (i = 0; i < base64.length(); i += 4) {
            int block = (((getValue(base64.charAt(i)) << 18) + (getValue(base64.charAt(i + 1)) << 12)) + (getValue(base64.charAt(i + 2)) << 6)) + getValue(base64.charAt(i + 3));
            int j = 0;
            while (j < 3 && rawIndex + j < raw.length) {
                raw[rawIndex + j] = (byte) ((block >> ((2 - j) * 8)) & 255);
                j++;
            }
            rawIndex += 3;
        }
        return raw;
    }

    protected static int getValue(char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 65;
        }
        if (c >= 'a' && c <= 'z') {
            return (c - 97) + 26;
        }
        if (c >= '0' && c <= '9') {
            return (c - 48) + 52;
        }
        if (c == '+') {
            return 62;
        }
        if (c == '/') {
            return 63;
        }
        if (c == '=') {
            return 0;
        }
        return -1;
    }
}
