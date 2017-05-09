package SupportClasses;

import java.io.IOException;

/* compiled from: ManifestModifier */
class ResStringChunk {
    int chunkSize;
    int chunkTag;
    byte[] resStrTableHdr = new byte[28];
    byte[] strBuffer;
    int stringCount;
    int stringOffset;
    int[] stringOffsetArray;
    String[] stringValues;
    int styleCount;
    int styleOffset;
    int unknown1;

    ResStringChunk() {
    }

    public void parse(MyInputStream is) throws IOException {
        is.readBytes(this.resStrTableHdr);
        this.chunkTag = ManifestModifier.getInt(this.resStrTableHdr, 0);
        this.chunkSize = ManifestModifier.getInt(this.resStrTableHdr, 4);
        this.stringCount = ManifestModifier.getInt(this.resStrTableHdr, 8);
        this.styleCount = ManifestModifier.getInt(this.resStrTableHdr, 12);
        this.stringOffset = ManifestModifier.getInt(this.resStrTableHdr, 20);
        this.styleOffset = ManifestModifier.getInt(this.resStrTableHdr, 24);
        this.stringOffsetArray = new int[this.stringCount];
        this.stringValues = new String[this.stringCount];
        is.readIntArray(this.stringOffsetArray);
        int strValuePaddings = (this.stringOffset + 8) - ((this.stringCount * 4) + 36);
        ManifestModifier.log("strValuePaddings=%d", Integer.valueOf(strValuePaddings));
        is.skip(strValuePaddings);
        this.strBuffer = new byte[(((this.chunkSize - 28) - (this.stringCount * 4)) - strValuePaddings)];
        is.readBytes(this.strBuffer);
        parseString(this.strBuffer, this.stringOffsetArray, this.stringValues);
    }

    private static void parseString(byte[] buffer, int[] stringIdxOffset, String[] stringValues) throws IOException {
        for (int i = 0; i < stringIdxOffset.length; i++) {
            int curOffset = stringIdxOffset[i];
            int strLen = ManifestModifier.getShort(buffer, curOffset);
            if (strLen < 32768) {
                byte[] chars = new byte[strLen];
                for (int ii = 0; ii < strLen; ii++) {
                    chars[ii] = buffer[(curOffset + 2) + (ii * 2)];
                }
                stringValues[i] = new String(chars);
            }
            ManifestModifier.log("%d:\t%s", Integer.valueOf(i), stringValues[i]);
        }
    }

    public void replaceString(String oldStr, String newStr) {
        int i;
        int addedSize = (newStr.length() - oldStr.length()) * 2;
        this.chunkSize += addedSize;
        ManifestModifier.setInt(this.resStrTableHdr, 4, this.chunkSize);
        int targetIndex = -1;
        for (i = 0; i < this.stringCount; i++) {
            if (oldStr.equals(this.stringValues[i])) {
                targetIndex = i;
                ManifestModifier.log("find the target string at index %d", Integer.valueOf(i));
                this.stringValues[i] = newStr;
                break;
            }
        }
        if (targetIndex == -1) {
            ManifestModifier.log("cannot find the target string %s", oldStr);
            return;
        }
        int oldBufferOffset = 0;
        int newBufferOffset = 0;
        int[] newStrOffsetArray = new int[this.stringCount];
        byte[] newStrBuffer = new byte[(this.strBuffer.length + addedSize)];
        for (i = 0; i < this.stringCount; i++) {
            newStrOffsetArray[i] = newBufferOffset;
            if (i != targetIndex) {
                int oneStrLen = getStrTotalLength(this.strBuffer, oldBufferOffset);
                ManifestModifier.log("String Len: %d", Integer.valueOf(oneStrLen));
                System.arraycopy(this.strBuffer, oldBufferOffset, newStrBuffer, newBufferOffset, oneStrLen);
                oldBufferOffset += oneStrLen;
                newBufferOffset += oneStrLen;
            } else {
                ManifestModifier.setShort(newStrBuffer, newBufferOffset, newStr.length());
                newBufferOffset += 2;
                for (int ii = 0; ii < newStr.length(); ii++) {
                    newStrBuffer[(ii * 2) + newBufferOffset] = (byte) newStr.charAt(ii);
                    newStrBuffer[((ii * 2) + newBufferOffset) + 1] = (byte) 0;
                }
                newBufferOffset += newStr.length() * 2;
                ManifestModifier.setShort(newStrBuffer, newBufferOffset, 0);
                newBufferOffset += 2;
                oldBufferOffset += (oldStr.length() * 2) + 4;
            }
        }
        this.stringOffsetArray = newStrOffsetArray;
        this.strBuffer = newStrBuffer;
    }

    public void addString(String addedStr, int position) {
        int i;
        this.chunkSize += ((addedStr.length() + 2) * 2) + 4;
        this.stringCount++;
        this.stringOffset += 4;
        ManifestModifier.setInt(this.resStrTableHdr, 4, this.chunkSize);
        ManifestModifier.setInt(this.resStrTableHdr, 8, this.stringCount);
        ManifestModifier.setInt(this.resStrTableHdr, 20, this.stringOffset);
        String[] newStringValues = new String[this.stringCount];
        for (i = 0; i < this.stringCount; i++) {
            if (i < position) {
                newStringValues[i] = this.stringValues[i];
            } else if (i > position) {
                newStringValues[i] = this.stringValues[i - 1];
            } else {
                newStringValues[i] = addedStr;
            }
        }
        this.stringValues = newStringValues;
        int oldBufferOffset = 0;
        int newBufferOffset = 0;
        int[] newStrOffsetArray = new int[this.stringCount];
        byte[] newStrBuffer = new byte[(this.strBuffer.length + ((addedStr.length() + 2) * 2))];
        for (i = 0; i < this.stringCount; i++) {
            newStrOffsetArray[i] = newBufferOffset;
            if (i != position) {
                int oneStrLen = getStrTotalLength(this.strBuffer, oldBufferOffset);
                ManifestModifier.log("String Len: %d", Integer.valueOf(oneStrLen));
                System.arraycopy(this.strBuffer, oldBufferOffset, newStrBuffer, newBufferOffset, oneStrLen);
                oldBufferOffset += oneStrLen;
                newBufferOffset += oneStrLen;
            } else {
                ManifestModifier.setShort(newStrBuffer, newBufferOffset, addedStr.length());
                newBufferOffset += 2;
                for (int ii = 0; ii < addedStr.length(); ii++) {
                    newStrBuffer[(ii * 2) + newBufferOffset] = (byte) addedStr.charAt(ii);
                    newStrBuffer[((ii * 2) + newBufferOffset) + 1] = (byte) 0;
                }
                newBufferOffset += addedStr.length() * 2;
                ManifestModifier.setShort(newStrBuffer, newBufferOffset, 0);
                newBufferOffset += 2;
            }
        }
        this.stringOffsetArray = newStrOffsetArray;
        this.strBuffer = newStrBuffer;
    }

    private int getStrTotalLength(byte[] buf, int offset) {
        int sectionLen = 2;
        int len = ManifestModifier.getShort(buf, offset);
        if ((32768 & len) != 0) {
            len = ((len & 32767) << 16) | ManifestModifier.getShort(buf, offset + 2);
            sectionLen = 2 + 2;
        }
        return sectionLen + ((len * 2) + 2);
    }

    public void dump(MyFileOutput out) throws IOException {
        int lastStrPos = this.stringOffsetArray[this.stringCount - 1];
        int strValueLen = lastStrPos + getStrTotalLength(this.strBuffer, lastStrPos);
        this.chunkSize = ((this.stringCount * 4) + 28) + strValueLen;
        int paddingSize = this.chunkSize % 4;
        this.chunkSize += paddingSize;
        ManifestModifier.setInt(this.resStrTableHdr, 4, this.chunkSize);
        out.writeBytes(this.resStrTableHdr);
        out.writeIntArray(this.stringOffsetArray);
        out.writeBytes(this.strBuffer, 0, strValueLen);
        if (paddingSize > 0) {
            out.writeShort(0);
        }
    }

    public String getStringByIndex(int idx) {
        if (idx < 0 || idx >= this.stringValues.length) {
            return null;
        }
        return this.stringValues[idx];
    }
}
