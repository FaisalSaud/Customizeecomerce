package SupportClasses.asn1;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Asn1Set extends Asn1Data {
    protected ArrayList mList = new ArrayList();

    public Asn1Set add(Asn1Data asn1data) {
        this.mList.add(asn1data);
        return this;
    }

    public int getBodyLength() {
        int i = 0;
        Iterator iterator = this.mList.iterator();
        while (iterator.hasNext()) {
            i += ((Asn1Data) iterator.next()).getTotalLength();
        }
        return i;
    }

    public void write(OutputStream outputstream) throws IOException {
        outputstream.write(49);
        Asn1Data.writeLength(outputstream, getBodyLength());
        Iterator iterator = this.mList.iterator();
        while (iterator.hasNext()) {
            ((Asn1Data) iterator.next()).write(outputstream);
        }
    }
}
