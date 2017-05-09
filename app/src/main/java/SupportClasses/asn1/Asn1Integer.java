package SupportClasses.asn1;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

public class Asn1Integer extends Asn1Data {
    private byte[] mByte = this.mInt.toByteArray();
    private BigInteger mInt;

    public Asn1Integer(int i) {
        this.mInt = BigInteger.valueOf((long) i);
    }

    public Asn1Integer(BigInteger biginteger) {
        this.mInt = biginteger;
    }

    private boolean needLeadingZero() {
        return this.mInt.signum() > 0 && (this.mByte[0] & 128) != 0;
    }

    public int getBodyLength() {
        if (needLeadingZero()) {
            return this.mByte.length + 1;
        }
        return this.mByte.length;
    }

    public void write(OutputStream outputstream) throws IOException {
        outputstream.write(2);
        Asn1Data.writeLength(outputstream, getBodyLength());
        if (needLeadingZero()) {
            outputstream.write(0);
        }
        outputstream.write(this.mByte);
    }
}
