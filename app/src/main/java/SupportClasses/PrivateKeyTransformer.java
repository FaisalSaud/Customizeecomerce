package SupportClasses;

import java.io.IOException;
import java.io.InputStream;

public class PrivateKeyTransformer extends InputStream {
    private InputStream input;
    private int position = 0;

    public PrivateKeyTransformer(InputStream is) {
        this.input = is;
    }

    public int read() throws IOException {
        int ret = this.input.read();
        if (ret == -1) {
            return ret;
        }
        ret = transform((byte) ret, this.position) & 255;
        this.position++;
        return ret;
    }

    public int read(byte[] buf) throws IOException {
        return read(buf, 0, buf.length);
    }

    public int read(byte[] buf, int offset, int len) throws IOException {
        int ret = this.input.read(buf, offset, len);
        if (ret != -1) {
            for (int i = 0; i < ret; i++) {
                buf[offset + i] = transform(buf[offset + i], this.position + i);
            }
            this.position += ret;
        }
        return ret;
    }

    private byte transform(byte b, int _position) {
        int val = b & 255;
        if (_position % 16 == 0) {
            val++;
        }
        return (byte) (val ^ 85);
    }
}
