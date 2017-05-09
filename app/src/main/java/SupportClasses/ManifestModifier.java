package SupportClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class ManifestModifier {
    private MyInputStream is;
    private MyFileOutput out;

    public ManifestModifier(String inputFile, String outputFile) throws IOException {
        this.is = new MyInputStream(new FileInputStream(inputFile));
        File f = new File(outputFile);
        if (f.exists()) {
            f.delete();
        }
        RandomAccessFile outFile = new RandomAccessFile(outputFile, "rw");
        outFile.setLength(0);
        this.out = new MyFileOutput(outFile);
    }

    public ManifestModifier(InputStream input, String outputFile) throws IOException {
        this.is = new MyInputStream(input);
        File f = new File(outputFile);
        if (f.exists()) {
            f.delete();
        }
        RandomAccessFile outFile = new RandomAccessFile(outputFile, "rw");
        outFile.setLength(0);
        this.out = new MyFileOutput(outFile);
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: AddInstallLocationAttr.jar inputFile outputFile");
        } else {
            ManifestModifier manifestModifier = new ManifestModifier(args[0], args[1]);
        }
    }

    protected static int getInt(byte[] buf, int offset) {
        return (((buf[offset + 0] & 255) | ((buf[offset + 1] & 255) << 8)) | ((buf[offset + 2] & 255) << 16)) | ((buf[offset + 3] & 255) << 24);
    }

    protected static int getShort(byte[] buf, int offset) {
        return (buf[offset + 0] & 255) | ((buf[offset + 1] & 255) << 8);
    }

    protected static void setInt(byte[] buf, int offset, int value) {
        buf[offset + 0] = (byte) (value & 255);
        buf[offset + 1] = (byte) ((value >> 8) & 255);
        buf[offset + 2] = (byte) ((value >> 16) & 255);
        buf[offset + 3] = (byte) ((value >> 24) & 255);
    }

    protected static void setShort(byte[] buf, int offset, int value) {
        buf[offset + 0] = (byte) (value & 255);
        buf[offset + 1] = (byte) ((value >> 8) & 255);
    }

    protected static void log(String format, Object... arguments) {
    }

    public void renameString(String oldStr, String newStr) throws Exception {
        int headTag = this.is.readInt();
        int fileSize = this.is.readInt();
        this.out.writeInt(headTag);
        this.out.writeInt(fileSize);
        log("file size: %d", Integer.valueOf(fileSize));
        ResStringChunk strChunk = new ResStringChunk();
        strChunk.parse(this.is);
        log("ChunkSize of Resource String: %d", Integer.valueOf(strChunk.chunkSize));
        log("String Count: %d", Integer.valueOf(strChunk.stringCount));
        log("String Offset: 0x%x", Integer.valueOf(strChunk.stringOffset));
        if (strChunk.styleCount == 0 && strChunk.styleOffset == 0) {
            int oldSize = strChunk.chunkSize;
            strChunk.replaceString(oldStr, newStr);
            strChunk.dump(this.out);
            fileSize += strChunk.chunkSize - oldSize;
            log("new file size: %d", Integer.valueOf(fileSize));
            log("position: %d", Integer.valueOf(this.is.getPosition()));
            byte[] remain = new byte[fileSize];
            this.out.writeBytes(remain, 0, this.is.readBytes(remain));
            //log("remain len: %d", Integer.valueOf(readLen));
            this.out.writeInt(4, fileSize);
            this.out.close();
            return;
        }
        throw new Exception("Detected style information, not supported yet!");
    }
}
