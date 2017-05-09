package SupportClasses.asn1;

import java.io.IOException;
import java.io.OutputStream;

public class Asn1ObjectId extends Asn1Data {
    private int[] mId;

    public Asn1ObjectId(String s) {
        String[] as = s.split("\\.");
        this.mId = new int[as.length];
        for (int i = 0; i < as.length; i++) {
            this.mId[i] = Integer.parseInt(as[i]);
        }
    }

    public int getBodyLength() {
        if (this.mId.length < 2 || this.mId[0] < 0 || this.mId[0] > 2 || this.mId[1] < 0 || this.mId[1] > 39) {
            throw new IllegalArgumentException("Object identifier out of range");
        }
        int size = 1;
        for (int i = 2; i < this.mId.length; i++) {
            if (this.mId[i] > 16384) {
                size += 3;
            } else if (this.mId[i] > 128) {
                size += 2;
            } else {
                size++;
            }
        }
        return size;
    }

    public void write(OutputStream outputstream) throws IOException {
        outputstream.write(6);
        int size = getBodyLength();
        Asn1Data.writeLength(outputstream, getBodyLength());
        byte[] value = new byte[size];
        value[0] = (byte) ((this.mId[0] * 40) + this.mId[1]);
        int j = 1;
        int i = 2;
        while (i < this.mId.length) {
            int j2;
            int val = this.mId[i];
            if (val >= 16384) {
                j2 = j + 1;
                value[j] = (byte) ((val >> 14) | 128);
                val &= 16383;
            } else {
                j2 = j;
            }
            if (val >= 128) {
                j = j2 + 1;
                value[j2] = (byte) ((val >> 7) | 128);
                val &= 127;
            } else {
                j = j2;
            }
            j2 = j + 1;
            value[j] = (byte) val;
            i++;
            j = j2;
        }
        outputstream.write(value);
    }
}
