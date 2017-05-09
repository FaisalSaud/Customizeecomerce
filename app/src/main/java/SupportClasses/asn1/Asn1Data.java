package SupportClasses.asn1;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

public abstract class Asn1Data {
    protected int mLength;
    protected int mTag;

    public abstract int getBodyLength();

    public abstract void write(OutputStream outputStream) throws IOException;

    public static int getLengthLength(int i) {
        if (i <= 127) {
            return 1;
        }
        byte[] abyte0 = BigInteger.valueOf((long) i).toByteArray();
        if (abyte0[0] == (byte) 0) {
            return abyte0.length;
        }
        return abyte0.length + 1;
    }

    public static void writeLength(OutputStream outputstream, int i) throws IOException {
        if (i > 127) {
            byte[] abyte0 = BigInteger.valueOf((long) i).toByteArray();
            if (abyte0[0] == (byte) 0) {
                outputstream.write((abyte0.length - 1) | 128);
                outputstream.write(abyte0, 1, abyte0.length - 1);
                return;
            }
            outputstream.write(abyte0.length | 128);
            outputstream.write(abyte0);
            return;
        }
        outputstream.write(i);
    }

    public int getTotalLength() {
        return (getLengthLength(getBodyLength()) + 1) + getBodyLength();
    }
}
