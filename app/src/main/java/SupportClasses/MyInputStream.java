package SupportClasses;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: ManifestModifier */
class MyInputStream {
    int curOffset = 0;
    InputStream is;

    public MyInputStream(InputStream is) {
        this.is = is;
    }

    public int readBytes(byte[] buf) throws IOException {
        int ret = this.is.read(buf);
        if (ret > 0) {
            this.curOffset += ret;
        }
        return ret;
    }

    public void readBytes(byte[] buf, int off, int len) throws IOException {
        int ret = this.is.read(buf, off, len);
        if (ret > 0) {
            this.curOffset += ret;
        }
    }

    public int readShort() throws IOException {
        int low = this.is.read();
        int high = this.is.read();
        this.curOffset += 2;
        return (low & 255) | ((high & 255) << 8);
    }

    public int readInt() throws IOException {
        byte[] arr = new byte[4];
        this.is.read(arr);
        this.curOffset += 4;
        return ManifestModifier.getInt(arr, 0);
    }

    public void readIntArray(int[] arr) throws IOException {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = readInt();
        }
    }

    public void skip(int n) throws IOException {
        if (n > 0) {
            this.is.skip((long) n);
            this.curOffset += n;
            ManifestModifier.log("########## skip detected (should not happen) ##########", new Object[0]);
        }
    }

    public int getPosition() {
        return this.curOffset;
    }
}
