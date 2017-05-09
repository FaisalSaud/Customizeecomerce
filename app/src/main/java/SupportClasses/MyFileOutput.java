package SupportClasses;

import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: ManifestModifier */
class MyFileOutput {
    private RandomAccessFile outFile;
    private int writeLength = 0;

    public MyFileOutput(RandomAccessFile outFile) {
        this.outFile = outFile;
    }

    public int getWriteLength() {
        return this.writeLength;
    }

    public void writeBytes(byte[] buf) throws IOException {
        this.outFile.write(buf);
        this.writeLength += buf.length;
    }

    public void writeBytes(byte[] buf, int off, int len) throws IOException {
        this.outFile.write(buf, off, len);
        this.writeLength += len;
    }

    public void writeInt(int val) throws IOException {
        byte[] buf = new byte[4];
        ManifestModifier.setInt(buf, 0, val);
        this.outFile.write(buf);
        this.writeLength += 4;
    }

    public void writeShort(int val) throws IOException {
        byte[] buf = new byte[2];
        ManifestModifier.setShort(buf, 0, val);
        this.outFile.write(buf);
        this.writeLength += 2;
    }

    public void writeIntArray(int[] values) throws IOException {
        byte[] buf = new byte[(values.length * 4)];
        for (int i = 0; i < values.length; i++) {
            ManifestModifier.setInt(buf, i * 4, values[i]);
        }
        writeBytes(buf);
    }

    public void writeInt(int position, int val) throws IOException {
        long oldPosition = this.outFile.getFilePointer();
        this.outFile.seek((long) position);
        writeInt(val);
        this.outFile.seek(oldPosition);
    }

    public void close() throws IOException {
        this.outFile.close();
    }
}
