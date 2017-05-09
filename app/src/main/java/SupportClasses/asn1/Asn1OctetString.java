package SupportClasses.asn1;

import java.io.IOException;
import java.io.OutputStream;

public class Asn1OctetString extends Asn1Data {
    private byte[] mData;

    public Asn1OctetString(byte[] abyte0) {
        this.mData = abyte0;
    }

    public int getBodyLength() {
        return this.mData.length;
    }

    public void write(OutputStream outputstream) throws IOException {
        outputstream.write(4);
        Asn1Data.writeLength(outputstream, getBodyLength());
        outputstream.write(this.mData);
    }
}
