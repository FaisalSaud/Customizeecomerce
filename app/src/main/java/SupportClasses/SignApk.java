package SupportClasses;

import SupportClasses.asn1.Asn1Box;
import SupportClasses.asn1.Asn1Data;
import SupportClasses.asn1.Asn1IA5String;
import SupportClasses.asn1.Asn1Integer;
import SupportClasses.asn1.Asn1Null;
import SupportClasses.asn1.Asn1ObjectId;
import SupportClasses.asn1.Asn1OctetString;
import SupportClasses.asn1.Asn1PrintableString;
import SupportClasses.asn1.Asn1Sequence;
import SupportClasses.asn1.Asn1Set;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.security.DigestOutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;

import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SignApk {
    private static int ALIGNMENT = 4;
    private static final String CERT_RSA_NAME = "META-INF/CERT.RSA";
    private static final String CERT_SF_NAME = "META-INF/CERT.SF";
    private static final int LINE_LENGTH_LIMIT = 72;
    private static final byte[] LINE_SEPARATOR = new byte[]{(byte) 13, (byte) 10};
    private static final Name NAME_ATTRIBUTE = new Name("Name");
    private static final String OTACERT_NAME = "META-INF/com/android/otacert";
    private static final byte[] VALUE_SEPARATOR = new byte[]{(byte) 58, (byte) 32};
    static String[] noCompressExt = new String[]{".jpg", ".jpeg", ".png", ".gif", ".wav", ".mp2", ".mp3", ".ogg", ".aac", ".mpg", ".mpeg", ".mid", ".midi", ".smf", ".jet", ".rtttl", ".imy", ".xmf", ".mp4", ".m4a", ".m4v", ".3gp", ".3gpp", ".3g2", ".3gpp2", ".amr", ".awb", ".wma", ".wmv"};
    private static Pattern stripPattern = Pattern.compile("^META-INF/(.*)[.](SF|RSA|DSA)$");

    private static class CountingOutputStream extends OutputStream {
        private long mCount = 0;
        private OutputStream mOut;

        public CountingOutputStream(OutputStream out) {
            this.mOut = out;
        }

        public long getCount() {
            return this.mCount;
        }

        public void write(byte[] b, int off, int len) throws IOException {
            this.mOut.write(b, off, len);
            this.mCount += (long) len;
        }

        public void write(int b) throws IOException {
            this.mOut.write(b);
            this.mCount++;
        }

        public void close() throws IOException {
            this.mOut.close();
        }

        public void flush() throws IOException {
            this.mOut.flush();
        }
    }

    private static class SignatureOutputStream extends FilterOutputStream {
        private int mCount = 0;
        private Signature mSignature;

        public SignatureOutputStream(OutputStream out, Signature sig) {
            super(out);
            this.mSignature = sig;
        }

        public void write(int b) throws IOException {
            try {
                this.mSignature.update((byte) b);
                super.write(b);
                this.mCount++;
            } catch (SignatureException e) {
                throw new IOException("SignatureException: " + e);
            }
        }
    }

    private static X509Certificate readPublicKey(File file) throws IOException, GeneralSecurityException {
        FileInputStream input = new FileInputStream(file);
        try {
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(input);
            return x509Certificate;
        } finally {
            input.close();
        }
    }

    private static X509Certificate readPublicKey(InputStream input) throws IOException, GeneralSecurityException {
        try {
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(input);
            return x509Certificate;
        } finally {
            input.close();
        }
    }

    private static String readPassword(InputStream keyInput) {
        System.out.print("Enter password for private key (password will not be hidden): ");
        System.out.flush();
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private static KeySpec decryptPrivateKey(byte[] encryptedPrivateKey, InputStream keyInput) throws GeneralSecurityException {
        try {
            EncryptedPrivateKeyInfo epkInfo = new EncryptedPrivateKeyInfo(encryptedPrivateKey);
            Key key = SecretKeyFactory.getInstance(epkInfo.getAlgName()).generateSecret(new PBEKeySpec(readPassword(keyInput).toCharArray()));
            Cipher cipher = Cipher.getInstance(epkInfo.getAlgName());
            cipher.init(2, key, epkInfo.getAlgParameters());
            try {
                return epkInfo.getKeySpec(cipher);
            } catch (InvalidKeySpecException ex) {
                System.err.println("Password for private key may be bad.");
                throw ex;
            }
        } catch (IOException e) {
            return null;
        }
    }

    private static PrivateKey readPrivateKey(InputStream input) throws IOException, GeneralSecurityException {
        PrivateKey generatePrivate;
        KeySpec spec = null;
        try {
            byte[] bytes = new byte[8192];
            input.read(bytes);
            spec = decryptPrivateKey(bytes, input);
            if (spec == null) {
                spec = new PKCS8EncodedKeySpec(bytes);
            }
            generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(spec);
        } catch (InvalidKeySpecException e) {
            generatePrivate = KeyFactory.getInstance("DSA").generatePrivate(spec);
        } finally {
            input.close();
        }
        return generatePrivate;
    }

    private static Manifest addDigestsToManifest(JarFile jar, Map<String, String> repalces, Map<String, String> addedAssetFiles, Set<String> deletedFiles) throws IOException, GeneralSecurityException {
        int num;
        Manifest input = jar.getManifest();
        Manifest output = new Manifest();
        Attributes main = output.getMainAttributes();
        if (input != null) {
            main.putAll(input.getMainAttributes());
        } else {
            main.putValue("Manifest-Version", "1.0");
            main.putValue("Created-By", "1.0 (Android SignApk)");
        }
        MessageDigest md = MessageDigest.getInstance("SHA1");
        byte[] buffer = new byte[4096];
        TreeMap<String, JarEntry> byName = new TreeMap();
        Enumeration<JarEntry> e = jar.entries();
        while (e.hasMoreElements()) {
            JarEntry entry = (JarEntry) e.nextElement();
            byName.put(entry.getName(), entry);
        }
        for (JarEntry entry2 : byName.values()) {
            FileInputStream fis;
            String name = entry2.getName();
            if (!((deletedFiles != null && deletedFiles.contains(name)) || entry2.isDirectory() || name.equals("META-INF/MANIFEST.MF") || name.equals(CERT_SF_NAME) || name.equals(CERT_RSA_NAME) || name.equals(OTACERT_NAME))) {
                if (stripPattern == null || !stripPattern.matcher(name).matches()) {
                    if (!repalces.keySet().contains(name)) {
                        InputStream data = jar.getInputStream(entry2);
                        while (true) {
                            num = data.read(buffer);
                            if (num <= 0) {
                                break;
                            }
                            md.update(buffer, 0, num);
                        }
                    } else {
                        fis = new FileInputStream((String) repalces.get(name));
                        while (true) {
                            num = fis.read(buffer);
                            if (num <= 0) {
                                break;
                            }
                            md.update(buffer, 0, num);
                        }
                        fis.close();
                    }
                    Attributes attr = new Attributes();
                    attr.putValue("SHA1-Digest", MyBase64.encode(md.digest()));
                    output.getEntries().put(name, attr);
                    if (addedAssetFiles != null) {
                        for (String name2 : addedAssetFiles.keySet()) {
                            fis = new FileInputStream((String) addedAssetFiles.get(name2));
                            while (true) {
                                num = fis.read(buffer);
                                if (num <= 0) {
                                    break;
                                }
                                md.update(buffer, 0, num);
                            }
                            fis.close();
                            attr = new Attributes();
                            attr.putValue("SHA1-Digest", MyBase64.encode(md.digest()));
                            output.getEntries().put(name2, attr);
                        }
                    }
                }
            }
        }

        return output;
    }

    private static void addOtacert(JarOutputStream outputJar, InputStream publicKeyInput, long timestamp, Manifest manifest) throws IOException, GeneralSecurityException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        JarEntry je = new JarEntry(OTACERT_NAME);
        je.setTime(timestamp);
        outputJar.putNextEntry(je);
        byte[] b = new byte[4096];
        while (true) {
            int read = publicKeyInput.read(b);
            if (read == -1) {
                Attributes attr = new Attributes();
                attr.putValue("SHA1-Digest", MyBase64.encode(md.digest()));
                manifest.getEntries().put(OTACERT_NAME, attr);
                return;
            }
            outputJar.write(b, 0, read);
            md.update(b, 0, read);
        }
    }

    private static void writeEntry(OutputStream paramOutputStream, Name paramName, String paramString, CharsetEncoder paramCharsetEncoder, ByteBuffer paramByteBuffer) throws IOException {
        String str = paramName.toString();
        paramOutputStream.write(str.getBytes("US-ASCII"));
        paramOutputStream.write(VALUE_SEPARATOR);
        paramCharsetEncoder.reset();
        paramByteBuffer.clear().limit((72 - str.length()) - 2);
        CharBuffer localCharBuffer = CharBuffer.wrap(paramString);
        while (true) {
            CoderResult localCoderResult = paramCharsetEncoder.encode(localCharBuffer, paramByteBuffer, true);
            if (CoderResult.UNDERFLOW == localCoderResult) {
                localCoderResult = paramCharsetEncoder.flush(paramByteBuffer);
            }
            paramOutputStream.write(paramByteBuffer.array(), paramByteBuffer.arrayOffset(), paramByteBuffer.position());
            paramOutputStream.write(LINE_SEPARATOR);
            if (CoderResult.UNDERFLOW != localCoderResult) {
                paramOutputStream.write(32);
                paramByteBuffer.clear().limit(71);
            } else {
                return;
            }
        }
    }

    static void write(Manifest manifest, OutputStream outputstream) throws IOException {
        CharsetEncoder charsetencoder = Charset.forName("UTF-8").newEncoder();
        ByteBuffer bytebuffer = ByteBuffer.allocate(LINE_LENGTH_LIMIT);
        Attributes attributes = manifest.getMainAttributes();
        Name versionName = Name.MANIFEST_VERSION;
        String versionValue = attributes.getValue(versionName);
        if (versionValue == null) {
            versionName = Name.SIGNATURE_VERSION;
            versionValue = attributes.getValue(versionName);
        }
        if (versionValue != null) {
            writeEntry(outputstream, versionName, versionValue, charsetencoder, bytebuffer);
        }
        for (/*Name*/ Object name2 : attributes.keySet()) {
            if (!name2.equals(Name.SIGNATURE_VERSION)) {
                writeEntry(outputstream, (Name) name2, attributes.getValue((Name) name2), charsetencoder, bytebuffer);
            }
        }

        outputstream.write("\r\n".getBytes());
        for (Entry<String, Attributes> e : manifest.getEntries().entrySet()) {
            StringBuffer buffer = new StringBuffer("Name: ");
            String value = (String) e.getKey();
            if (value != null) {
                byte[] vb = value.getBytes("UTF8");
                value = new String(vb, 0, 0, vb.length);
            }
            buffer.append(value);
            buffer.append("\r\n");
            make72Safe(buffer);
            outputstream.write(buffer.toString().getBytes());
            attributes = (Attributes) e.getValue();
            for (/*Name*/Object name22 : attributes.keySet()) {
                if (!name22.equals(Name.MANIFEST_VERSION)) {
                    writeEntry(outputstream, (Name) name22, attributes.getValue((Name) name22), charsetencoder, bytebuffer);
                }
            }
            outputstream.write("\r\n".getBytes());
        }
    }

    static void make72Safe(StringBuffer line) {
        int length = line.length();
        if (length > LINE_LENGTH_LIMIT) {
            int index = 70;
            while (index < length - 2) {
                line.insert(index, "\r\n ");
                index += LINE_LENGTH_LIMIT;
                length += 3;
            }
        }
    }

    private static void writeSignatureFile(Manifest manifest, SignatureOutputStream out) throws IOException, GeneralSecurityException {
        Manifest sf = new Manifest();
        Attributes main = sf.getMainAttributes();
        main.putValue("Signature-Version", "1.0");
        main.putValue("Created-By", "1.0 (Android SignApk)");
        MessageDigest md = MessageDigest.getInstance("SHA1");
        PrintStream print = new PrintStream(new DigestOutputStream(new ByteArrayOutputStream(), md), true, "UTF-8");
        manifest.write(print);
        print.flush();
        main.putValue("SHA1-Digest-Manifest", MyBase64.encode(md.digest()));
        for (Entry<String, Attributes> entry : manifest.getEntries().entrySet()) {
            print.print("Name: " + ((String) entry.getKey()) + "\r\n");
            for (Entry<Object, Object> att : ((Attributes) entry.getValue()).entrySet()) {
                print.print(att.getKey() + ": " + att.getValue() + "\r\n");
            }
            print.print("\r\n");
            print.flush();
            Attributes sfAttr = new Attributes();
            sfAttr.putValue("SHA1-Digest", MyBase64.encode(md.digest()));
            sf.getEntries().put((String) entry.getKey(), sfAttr);
        }
        write(sf, out);
    }

    private static void writeSignatureBlockM(Signature signature, X509Certificate publicKey, OutputStream out) throws SignatureException, IOException, CertificateEncodingException {
        BigInteger serialNumber = publicKey.getSerialNumber();
        Map<String, String> issueInfo = new HashMap();
        for (String v : publicKey.getIssuerDN().toString().split(",")) {
            String[] keyvalue = v.trim().split("=");
            if (keyvalue.length == 2) {
                issueInfo.put(keyvalue[0], keyvalue[1]);
            }
        }
        String strC = (String) issueInfo.get("C");
        String strST = (String) issueInfo.get("ST");
        String strL = (String) issueInfo.get("L");
        String strO = (String) issueInfo.get("O");
        String strOU = (String) issueInfo.get("OU");
        String strCN = (String) issueInfo.get("CN");
        String strMail = (String) issueInfo.get("OID.1.2.840.113549.1.9.1");
        if (strMail != null && strMail.startsWith("#")) {
            strMail = fromHex(strMail.substring(5));
        }
        Asn1Sequence signerSequence = new Asn1Sequence();
        if (strC != null) {
            signerSequence.add(new Asn1Set().add(new Asn1Sequence().add(new Asn1ObjectId("2.5.4.6")).add(new Asn1PrintableString(strC))));
        }
        if (strST != null) {
            signerSequence.add(new Asn1Set().add(new Asn1Sequence().add(new Asn1ObjectId("2.5.4.8")).add(new Asn1PrintableString(strST))));
        }
        if (strL != null) {
            signerSequence.add(new Asn1Set().add(new Asn1Sequence().add(new Asn1ObjectId("2.5.4.7")).add(new Asn1PrintableString(strL))));
        }
        if (strO != null) {
            signerSequence.add(new Asn1Set().add(new Asn1Sequence().add(new Asn1ObjectId("2.5.4.10")).add(new Asn1PrintableString(strO))));
        }
        if (strOU != null) {
            signerSequence.add(new Asn1Set().add(new Asn1Sequence().add(new Asn1ObjectId("2.5.4.11")).add(new Asn1PrintableString(strOU))));
        }
        if (strCN != null) {
            signerSequence.add(new Asn1Set().add(new Asn1Sequence().add(new Asn1ObjectId("2.5.4.3")).add(new Asn1PrintableString(strCN))));
        }
        if (strMail != null) {
            signerSequence.add(new Asn1Set().add(new Asn1Sequence().add(new Asn1ObjectId("1.2.840.113549.1.9.1")).add(new Asn1IA5String(strMail))));
        }
        byte[] arrayOfByte = signature.sign();
        Asn1Set localAsn1Set = new Asn1Set();
        localAsn1Set.add(new Asn1Sequence().add(new Asn1Integer(1)).add(new Asn1Sequence().add(signerSequence).add(new Asn1Integer(serialNumber))).add(new Asn1Sequence().add(new Asn1ObjectId("1.3.14.3.2.26")).add(new Asn1Null())).add(new Asn1Sequence().add(new Asn1ObjectId("1.2.840.113549.1.1.1")).add(new Asn1Null())).add(new Asn1OctetString(arrayOfByte)));
        Asn1Sequence localAsn1Sequence1 = new Asn1Sequence();
        localAsn1Sequence1.add(new Asn1Integer(1));
        localAsn1Sequence1.add(new Asn1Set().add(new Asn1Sequence().add(new Asn1ObjectId("1.3.14.3.2.26")).add(new Asn1Null())));
        localAsn1Sequence1.add(new Asn1Sequence().add(new Asn1ObjectId("1.2.840.113549.1.7.1")));
        localAsn1Sequence1.add(new Asn1Box(publicKey.getEncoded()));
        localAsn1Sequence1.add(localAsn1Set);
        Asn1Sequence localAsn1Sequence2 = new Asn1Sequence();
        localAsn1Sequence2.add(new Asn1ObjectId("1.2.840.113549.1.7.2"));
        localAsn1Sequence2.add(new Asn1Box(localAsn1Sequence1));
        localAsn1Sequence2.write(out);
    }

    private static String fromHex(String hexStr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hexStr.length() - 1; i += 2) {
            int high = hex2Int(hexStr.charAt(i));
            sb.append((char) ((high << 4) | hex2Int(hexStr.charAt(i + 1))));
        }
        return sb.toString();
    }

    private static int hex2Int(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c < 'A' || c > 'F') {
            return 0;
        }
        return (c - 65) + 10;
    }

    private static void signWholeOutputFile(byte[] zipData, OutputStream outputStream, X509Certificate publicKey, PrivateKey privateKey) throws IOException, GeneralSecurityException {
        if (zipData[zipData.length - 22] == (byte) 80 && zipData[zipData.length - 21] == (byte) 75 && zipData[zipData.length - 20] == (byte) 5 && zipData[zipData.length - 19] == (byte) 6) {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            signature.update(zipData, 0, zipData.length - 2);
            ByteArrayOutputStream temp = new ByteArrayOutputStream();
            byte[] message = "signed by SignApk".getBytes("UTF-8");
            temp.write(message);
            temp.write(0);
            writeSignatureBlockM(signature, publicKey, temp);
            int total_size = temp.size() + 6;
            if (total_size > 65535) {
                throw new IllegalArgumentException("signature is too big for ZIP file comment");
            }
            int signature_start = (total_size - message.length) - 1;
            temp.write(signature_start & 255);
            temp.write((signature_start >> 8) & 255);
            temp.write(255);
            temp.write(255);
            temp.write(total_size & 255);
            temp.write((total_size >> 8) & 255);
            temp.flush();
            byte[] b = temp.toByteArray();
            int i = 0;
            while (i < b.length - 3) {
                if (b[i] == (byte) 80 && b[i + 1] == (byte) 75 && b[i + 2] == (byte) 5 && b[i + 3] == (byte) 6) {
                    throw new IllegalArgumentException("found spurious EOCD header at " + i);
                }
                i++;
            }
            outputStream.write(zipData, 0, zipData.length - 2);
            outputStream.write(total_size & 255);
            outputStream.write((total_size >> 8) & 255);
            temp.writeTo(outputStream);
            return;
        }
        throw new IllegalArgumentException("zip data already has an archive comment");
    }

    static boolean isNoCompressFileType(String entryName) {
        for (String endsWith : noCompressExt) {
            if (entryName.endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }

    private static void copyFiles(Manifest manifest, JarFile in, JarOutputStream out, CountingOutputStream outCount, long timestamp, Map<String, String> jarPath2FilePath, Map<String, String> addedAssetFiles, Set<String> set) throws IOException {
        byte[] buffer = new byte[4096];
        if (addedAssetFiles != null) {
            jarPath2FilePath.putAll(addedAssetFiles);
        }
        List<String> names = new ArrayList(manifest.getEntries().keySet());
        Collections.sort(names);
        boolean firstEntry = true;
        for (String name : names) {
            long offset = outCount.getCount();
            if (firstEntry) {
                firstEntry = false;
                offset += 4;
            }
            JarEntry outEntry;
            int needed;
            int num;
            if (jarPath2FilePath.containsKey(name)) {
                String filePath = (String) jarPath2FilePath.get(name);
                outEntry = new JarEntry(name);
                outEntry.setTime(timestamp);
                if (isNoCompressFileType(name)) {
                    try {
                        storeTheEntry(outEntry, filePath);
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    needed = (ALIGNMENT - ((int) ((offset + ((long) (name.length() + 30))) % ((long) ALIGNMENT)))) % ALIGNMENT;
                    if (needed != 0) {
                        outEntry.setExtra(new byte[needed]);
                    }
                }
                out.putNextEntry(outEntry);
                FileInputStream fileinput = new FileInputStream(filePath);
                while (true) {
                    num = fileinput.read(buffer);
                    if (num <= 0) {
                        break;
                    }
                    out.write(buffer, 0, num);
                }
                fileinput.close();
                out.closeEntry();
                out.flush();
            } else {
                JarEntry inEntry = in.getJarEntry(name);
                if (inEntry.getMethod() == ZipEntry.STORED) {
                    outEntry = new JarEntry(inEntry);
                    needed = (ALIGNMENT - ((int) ((offset + ((long) (name.length() + 30))) % ((long) ALIGNMENT)))) % ALIGNMENT;
                    if (needed != 0) {
                        outEntry.setExtra(new byte[needed]);
                    } else {
                        outEntry.setExtra(null);
                    }
                } else {
                    outEntry = new JarEntry(name);
                }
                outEntry.setTime(timestamp);
                out.putNextEntry(outEntry);
                InputStream data = in.getInputStream(inEntry);
                while (true) {
                    num = data.read(buffer);
                    if (num <= 0) {
                        break;
                    }
                    out.write(buffer, 0, num);
                }
                data.close();
                out.closeEntry();
                out.flush();
            }
        }
    }

    private static void storeTheEntry(JarEntry outEntry, String filePath) throws Throwable {
        Throwable th;
        outEntry.setMethod(ZipEntry.STORED);
        File f = new File(filePath);
        outEntry.setSize(f.length());
        FileInputStream fis = null;
        try {
            CRC32 crc32 = new CRC32();
            crc32.reset();
            byte[] b = new byte[8192];
            FileInputStream fis2 = new FileInputStream(f);
            while (true) {
                int num = fis2.read(b);
                if (num <= 0) {
                    break;
                }
                try {
                    crc32.update(b, 0, num);
                } catch (Throwable th2) {
                    th = th2;
                    fis = fis2;
                }
            }
            outEntry.setCrc(crc32.getValue());
            if (fis2 != null) {
                try {
                    fis2.close();
                } catch (IOException e) {
                }
            }
        } catch (Throwable th3) {
            th = th3;
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e2) {
                }
            }
            throw th;
        }
    }

    public static void main(String[] args) throws Throwable {
        if (args.length != 4) {
            System.err.println("Usage: signapk publickey.x509[.pem] privatekey.pk8 input.jar output.jar");
            System.exit(2);
        }
        signAPK(new FileInputStream(args[0]), new FileInputStream(args[1]), args[2], args[3], null, null, 9);
    }

    protected void test() {
    }

    public static void signAPK(InputStream publicKeyInput, InputStream privateKeyInput, String inputApkPath, String outputApkPath, Map<String, String> jarPath2FilePath, int level) throws Throwable {
        signAPK(publicKeyInput, privateKeyInput, inputApkPath, outputApkPath, (Map) jarPath2FilePath, null, level);
    }

    public static void signAPK(InputStream publicKeyInput, InputStream privateKeyInput, String inputApkPath, String outputApkPath, Map<String, String> jarPath2FilePath, int level, boolean encrypted) throws Throwable {
        if (encrypted) {
            signAPK(publicKeyInput, new PrivateKeyTransformer(privateKeyInput), inputApkPath, outputApkPath, (Map) jarPath2FilePath, null, level);
        }
    }

    public static void signAPK(InputStream publicKeyInput, InputStream privateKeyInput, String inputApkPath, String outputApkPath, Map<String, String> jarPath2FilePath, Map<String, String> addedAssetFiles, int level) throws Throwable {
        OutputStream outputFile;
        Exception e;
        Throwable th;
        OutputStream outputStream;
        FileOutputStream outputFile2 = null;
        JarFile inputJar;
        JarOutputStream jarOutputStream;
        try {
            OutputStream outputStream2;
            X509Certificate publicKey = readPublicKey(publicKeyInput);
            long timestamp = publicKey.getNotBefore().getTime() + 3600000;
            PrivateKey privateKey = readPrivateKey(privateKeyInput);
            inputJar = new JarFile(new File(inputApkPath), false);
            outputFile = new FileOutputStream(outputApkPath);
            outputStream2 = outputFile;
            CountingOutputStream outCount = new CountingOutputStream(outputFile);
            jarOutputStream = new JarOutputStream(outCount);
            jarOutputStream.setLevel(level);
            Manifest manifest = addDigestsToManifest(inputJar, jarPath2FilePath, addedAssetFiles, null);
            copyFiles(manifest, inputJar, jarOutputStream, outCount, timestamp, jarPath2FilePath, addedAssetFiles, null);
            JarEntry je = new JarEntry("META-INF/MANIFEST.MF");
            je.setTime(timestamp);
            jarOutputStream.putNextEntry(je);
            manifest.write(jarOutputStream);
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            je = new JarEntry(CERT_SF_NAME);
            je.setTime(timestamp);
            jarOutputStream.putNextEntry(je);
            writeSignatureFile(manifest, new SignatureOutputStream(jarOutputStream, signature));
            je = new JarEntry(CERT_RSA_NAME);
            je.setTime(timestamp);
            jarOutputStream.putNextEntry(je);
            writeSignatureBlockM(signature, publicKey, jarOutputStream);
            jarOutputStream.close();
            outputStream2.flush();
            if (inputJar != null) {
                try {
                    inputJar.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            if (outputFile != null) {
                outputFile.close();
                outputStream = outputFile;
                return;
            }
            outputStream = outputFile;
        } catch (Exception e1) {
        }
    }


    public static void signAPK(InputStream publicKeyInput, InputStream privateKeyInput, String inputApkPath, String outputApkPath, Map<String, String> addedFiles, Set<String> deletedFiles, Map<String, String> replacedFiles, int level) {
        JarFile inputJar;
        Exception e;
        OutputStream outputStream;
        Throwable th;
        FileOutputStream outputFile = null;
        JarOutputStream outputJar;
        try {
            X509Certificate publicKey = readPublicKey(publicKeyInput);
            long timestamp = publicKey.getNotBefore().getTime() + 3600000;
            PrivateKey privateKey = readPrivateKey(privateKeyInput);
            inputJar = new JarFile(new File(inputApkPath), false);
            OutputStream fileOutputStream = new FileOutputStream(outputApkPath);
            OutputStream outputStream2 = fileOutputStream;
            CountingOutputStream outCount = new CountingOutputStream(fileOutputStream);
            outputJar = new JarOutputStream(outCount);
            outputJar.setLevel(level);
            Manifest manifest = addDigestsToManifest(inputJar, replacedFiles, addedFiles, deletedFiles);
            copyFiles(manifest, inputJar, outputJar, outCount, timestamp, replacedFiles, addedFiles, deletedFiles);
            JarEntry je = new JarEntry("META-INF/MANIFEST.MF");
            je.setTime(timestamp);
            outputJar.putNextEntry(je);
            manifest.write(outputJar);
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            je = new JarEntry(CERT_SF_NAME);
            je.setTime(timestamp);
            outputJar.putNextEntry(je);
            writeSignatureFile(manifest, new SignatureOutputStream(outputJar, signature));
            je = new JarEntry(CERT_RSA_NAME);
            je.setTime(timestamp);
            outputJar.putNextEntry(je);
            writeSignatureBlockM(signature, publicKey, outputJar);
            outputJar.close();
            outputStream2.flush();
            if (inputJar != null) {
                try {
                    inputJar.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
                outputStream = fileOutputStream;
                return;
            }
            outputStream = fileOutputStream;

        } catch (Exception e1) {
        }
    }
}