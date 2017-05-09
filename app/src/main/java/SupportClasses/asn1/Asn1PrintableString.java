package SupportClasses.asn1;

import java.io.IOException;
import java.io.OutputStream;

public class Asn1PrintableString extends Asn1Data {
    protected String mData;

    public Asn1PrintableString(String s) {
        this.mData = s;
    }

    public int getBodyLength() {
        return this.mData.length();
    }

    public void write(OutputStream outputstream) throws IOException {
        outputstream.write(19);
        Asn1Data.writeLength(outputstream, getBodyLength());
        outputstream.write(this.mData.getBytes());
    }
}
