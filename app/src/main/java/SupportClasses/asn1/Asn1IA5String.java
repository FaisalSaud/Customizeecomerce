package SupportClasses.asn1;

import java.io.IOException;
import java.io.OutputStream;

public class Asn1IA5String extends Asn1Data {
    protected String mData;

    public Asn1IA5String(String s) {
        this.mData = s;
    }

    public int getBodyLength() {
        return this.mData.length();
    }

    public void write(OutputStream outputstream) throws IOException {
        outputstream.write(22);
        Asn1Data.writeLength(outputstream, getBodyLength());
        outputstream.write(this.mData.getBytes());
    }
}
