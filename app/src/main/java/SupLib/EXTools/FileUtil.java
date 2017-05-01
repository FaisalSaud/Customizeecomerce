package SupLib.EXTools;

import android.content.Context;
import android.util.Log;





import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
    boolean verbose;

    public FileUtil() {
        this(false);
    }

    public FileUtil(boolean verbose) {
        this.verbose = false;
        this.verbose = verbose;
    }

    public boolean existFile(String path) {
        return new File(path).exists();
    }

    public boolean makeDir(String path) {
        if (existFile(path)) {
            return false;
        }
        return new File(path).mkdirs();
    }

//    public void copyFileFromAsset(Context context, String assetFile, String diskFile) throws IOException {
//        IOException e;
//        Throwable th;
//        InputStream is = null;
//        FileOutputStream fileOutputStream = null;
//        try {
//            is = context.getAssets().open(assetFile);
//            FileOutputStream fos = new FileOutputStream(diskFile, false);
//            try {
//                byte[] buff = new byte[PKIFailureInfo.badRecipientNonce];
//                while (true) {
//                    int length = is.read(buff);
//                    if (length <= 0) {
//                        break;
//                    }
//                    fos.write(buff, 0, length);
//                }
//                if (this.verbose) {
//                    Log.d(getClass().getSimpleName(), "assetFile =>" + diskFile + " copy success.");
//                }
//                if (is != null) {
//                    try {
//                        is.close();
//                    } catch (IOException e2) {
//                        e2.printStackTrace();
//                    }
//                }
//                if (fos != null) {
//                    try {
//                        fos.close();
//                    } catch (IOException e22) {
//                        e22.printStackTrace();
//                    }
//                }
//            } catch (IOException e3) {
//
//                fileOutputStream = fos;
//                try {
//
//                } catch (Throwable th2) {
//                    th = th2;
//                }
//            } catch (Throwable th3) {
//                th = th3;
//                fileOutputStream = fos;
//                if (is != null) {
//                    try {
//                        is.close();
//                    } catch (IOException e222) {
//                        e222.printStackTrace();
//                    }
//                }
//                if (fileOutputStream != null) {
//                    try {
//                        fileOutputStream.close();
//                    } catch (IOException e2222) {
//                        e2222.printStackTrace();
//                    }
//                }
//                throw null;
//            }
//        } catch (IOException e4) {
//
//            throw null;
//        }
//    }

    public void copyDirectory(File sourceLocation, File targetLocation) throws IOException {
        if (!sourceLocation.isDirectory()) {
            //copyFile(sourceLocation.getAbsolutePath(), targetLocation.getAbsolutePath());
        } else if (targetLocation.exists() || targetLocation.mkdirs()) {
            String[] children = sourceLocation.list();
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    copyDirectory(new File(sourceLocation, children[i]), new File(targetLocation, children[i]));
                }
            }
        } else {
            throw new IOException("Cannot create dir " + targetLocation.getAbsolutePath());
        }
    }

//    public void copyFile(String srcPath, String destPath) throws IOException {
//        IOException e;
//        Throwable th;
//        FileInputStream fis = null;
//        FileOutputStream fos = null;
//        try {
//            FileInputStream fis2 = new FileInputStream(srcPath);
//
//            FileOutputStream fos2 = new FileOutputStream(destPath, false);
//
//            byte[] buff = new byte[PKIFailureInfo.badRecipientNonce];
//            if (this.verbose) {
//                Log.d(getClass().getSimpleName(), "src=" + srcPath + ",dest=" + destPath);
//            }
//            while (true) {
//                int length = fis2.read(buff);
//                if (length <= 0) {
//                    break;
//                }
//                fos2.write(buff, 0, length);
//            }
//            if (fis2 != null) {
//                try {
//                    fis2.close();
//                } catch (IOException e2) {
//                    e2.printStackTrace();
//                }
//            }
//            if (fos2 != null) {
//                try {
//                    fos2.close();
//                } catch (IOException e22) {
//                    e22.printStackTrace();
//                }
//            }
//
//        } catch (Throwable th3) {
//            th = th3;
//
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e222) {
//                    e222.printStackTrace();
//                }
//            }
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e2222) {
//                    e2222.printStackTrace();
//                }
//            }
//
//
//            if (fis != null) {
//                fis.close();
//            }
//            if (fos != null) {
//                fos.close();
//            }
////                throw th;
//        }
//
//    }

    public void deleteDir(String path) {
        deleteDir(path, true);
    }

    public void deleteDir(File file) {
        deleteDir(file, true);
    }

    public void deleteDir(File file, boolean deleteRootDir) {
        if (file.exists()) {
            File[] fileArr = file.listFiles();
            if (fileArr != null) {
                for (File subFile : fileArr) {
                    if (subFile.isDirectory()) {
                        deleteDir(subFile);
                        subFile.delete();
                    }
                    if (subFile.isFile()) {
                        subFile.delete();
                    }
                }
            }
            if (deleteRootDir) {
                file.delete();
            }
        }
    }

    public void deleteDir(String path, boolean deleteRootDir) {
        deleteDir(new File(path), deleteRootDir);
    }

    public void deleteFile(String path) {
        deleteFile(new File(path));
    }

    public void deleteFile(File file) {
        file.delete();
    }

    public void writeFile(String filePath, String str) throws IOException {
        IOException e;
        Throwable th;
        int endIdx = filePath.lastIndexOf(File.separator);
        if (endIdx > 0) {
            makeDir(filePath.substring(0, endIdx));
        }
        FileWriter fw = null;
        try {
            FileWriter fw2 = new FileWriter(new File(filePath), false);
            try {
                fw2.write(str);
                fw2.flush();
                if (this.verbose) {
                    Log.d(getClass().getSimpleName(), filePath + " saved");
                }
                if (fw2 != null) {
                    try {
                        fw2.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (IOException e3) {
                e = e3;
                fw = fw2;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                fw = fw2;
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException e4) {
                    }
                }

            }
        } catch (IOException e5) {
            e = e5;
            throw e;
        }
    }

//    public String readingFile(String path) throws IOException {
//        return readingFile(new File(path));
//    }

//    public String readingFile(File file) throws IOException {
//        IOException e;
//        Throwable th;
//        StringBuilder sb = new StringBuilder();
//        FileReader fileReader = null;
//        try {
//            FileReader fr = new FileReader(file);
//            try {
//                char[] buff = new char[PKIFailureInfo.badRecipientNonce];
//                while (true) {
//                    int length = fr.read(buff);
//                    if (length <= 0) {
//                        break;
//                    }
//                    sb.append(new String(buff, 0, length));
//                }
//                if (fr != null) {
//                    try {
//                        fr.close();
//                    } catch (Exception e2) {
//                    }
//                }
//                return sb.toString();
//            } catch (IOException e3) {
//                e = e3;
//                fileReader = fr;
//                try {
//                    throw e;
//                } catch (Throwable th2) {
//                    th = th2;
//                }
//            } catch (Throwable th3) {
//                th = th3;
//                fileReader = fr;
//                if (fileReader != null) {
//                    try {
//                        fileReader.close();
//                    } catch (Exception e4) {
//                    }
//                }
//
//            }
//        } catch (IOException e5) {
//            e = e5;
//            throw e;
//        }
//    }

    public String readFile(String filePath) throws IOException {
        return readFile(new File(filePath));
    }

    public String readFile(File file) throws IOException {
        IOException e;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        Throwable th;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line).append("\n");
                } catch (IOException e2) {
                    e = e2;
                    bufferedReader = br;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = br;
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e3) {
                }
            }
            return sb.toString();
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (Exception e4) {
//                }
//            }

        } catch (IOException e5) {
            e = e5;
            try {
                throw e;
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return "";
    }

//    public String readFileFromAsset(Context context, String fileName) {
//        IOException e;
//        Throwable th;
//        BufferedReader bufferedReader = null;
//        StringBuilder sb = new StringBuilder();
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName.trim()), "utf-8"));
//            while (true) {
//                try {
//                    String line = br.readLine();
//                    if (line == null) {
//                        break;
//                    }
//                    sb.append(line).append(DefineXml.XML_LINE);
//                } catch (IOException e2) {
//                    e = e2;
//                    bufferedReader = br;
//                } catch (Throwable th2) {
//                    th = th2;
//                    bufferedReader = br;
//                }
//            }
//            if (br != null) {
//                try {
//                    br.close();
//                    bufferedReader = br;
//                } catch (IOException e3) {
//                    e3.printStackTrace();
//                    bufferedReader = br;
//                }
//            }
//        } catch (IOException e4) {
//
//            try {
//
//                if (bufferedReader != null) {
//                    try {
//                        bufferedReader.close();
//                    } catch (IOException e32) {
//                        e32.printStackTrace();
//                    }
//                }
//                return sb.toString();
//            } catch (Throwable th3) {
//                th = th3;
//                if (bufferedReader != null) {
//                    try {
//                        bufferedReader.close();
//                    } catch (IOException e322) {
//                        e322.printStackTrace();
//                    }
//                }
//
//            }
//        }
//        return sb.toString();
//    }
}
