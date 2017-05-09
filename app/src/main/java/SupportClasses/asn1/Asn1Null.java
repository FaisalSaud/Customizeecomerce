package SupportClasses.asn1;

import java.io.IOException;
import java.io.OutputStream;

public class Asn1Null extends Asn1Data {
    public int getBodyLength() {
        return 0;
    }

    public void write(OutputStream outputstream) throws IOException {
        outputstream.write(5);
        outputstream.write(0);
    }
}
