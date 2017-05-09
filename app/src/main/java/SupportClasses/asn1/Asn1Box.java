package SupportClasses.asn1;

import java.io.IOException;
import java.io.OutputStream;

public class Asn1Box extends Asn1Data {
    protected byte[] mByteData;
    protected Asn1Data mData;

    public Asn1Box(Asn1Data asn1data) {
        this.mData = asn1data;
        this.mByteData = null;
    }

    public Asn1Box(byte[] abyte0) {
        this.mData = null;
        this.mByteData = abyte0;
    }

    public int getBodyLength() {
        if (this.mData == null) {
            return this.mByteData.length;
        }
        return this.mData.getTotalLength();
    }

    public void write(OutputStream outputstream) throws IOException {
        outputstream.write(160);
        Asn1Data.writeLength(outputstream, getBodyLength());
        if (this.mData == null) {
            outputstream.write(this.mByteData);
        } else {
            this.mData.write(outputstream);
        }
    }
}
