package SupLib.EXTools;

import android.content.Context;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    private static void addDir(String srcDir, File file, ZipOutputStream zos, ArrayList<String> excepList) throws Exception {
        File[] subFiles = file.listFiles();
        if (subFiles.length == 0) {
            String fileName = file.getAbsolutePath();
           // addZipFile(srcDir, fileName.substring(srcDir.length(), fileName.length()), zos);
        }
        for (File zipEntry : subFiles) {
            if (zipEntry.isDirectory()) {
                addDir(srcDir, zipEntry, zos, excepList);
            }
//            if (zipEntry.isFile()) {
//                fileName = zipEntry.getAbsolutePath();
//                String name = fileName.substring(srcDir.length(), fileName.length());
//                boolean except = false;
//                Iterator it = excepList.iterator();
//                while (it.hasNext()) {
//                    if (name.contains((String) it.next())) {
//                        except = true;
//                        break;
//                    }
//                }
////                if (!except) {
////                    addZipFile(srcDir, name, zos);
////                }
//            }
        }
    }

    public static void makeZip(String zipFilePath, ArrayList<String> dirList, ArrayList<String> excepList) throws Exception {
        Exception e;
        Throwable th;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            ZipOutputStream zos2;
            FileOutputStream fos2 = new FileOutputStream(zipFilePath);
            try {
                zos2 = new ZipOutputStream(fos2);
            } catch (Exception e2) {
                e = e2;
                fos = fos2;
                try {
                    e.printStackTrace();
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    if (zos != null) {
                        try {
                            zos.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    //throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fos = fos2;
                if (zos != null) {
                    zos.close();
                }
                if (fos != null) {
                    fos.close();
                }

            }
            try {
                Iterator it = dirList.iterator();
                while (it.hasNext()) {
                    String string = (String) it.next();
                   // addDir(string, new File(string), zos2, excepList);
                }
//                if (zos2 != null) {
//                    try {
//                        zos2.close();
//                    } catch (IOException e322) {
//                        e322.printStackTrace();
//                    }
//                }
                if (fos2 != null) {
                    try {
                        fos2.close();
                    } catch (IOException e3222) {
                        e3222.printStackTrace();
                    }
                }
            } catch (Exception e4) {
                e = e4;
               // zos = zos2;
                fos = fos2;
                e.printStackTrace();
                throw e;
            } catch (Throwable th4) {
                th = th4;
//                zos = zos2;
                fos = fos2;
                if (zos != null) {
                    zos.close();
                }
                if (fos != null) {
                    fos.close();
                }

            }
        } catch (Exception e5) {
            e = e5;
            e.printStackTrace();
            throw e;
        }
    }

//    private static void addZipFile(String path, String fileName, ZipOutputStream zos) throws Exception {
//        Exception e;
//        Throwable th;
//        File file = new File(path + fileName);
//        FileInputStream fis = null;
//        BufferedInputStream bis = null;
//        try {
//            if (file.isDirectory() && file.listFiles().length == 0) {
//                zos.putNextEntry(new ZipEntry(fileName + File.separator));
//            } else {
//                FileInputStream fis2 = new FileInputStream(file);
//                try {
//                    BufferedInputStream bis2 = new BufferedInputStream(fis2);
//                    try {
//                        try {
//                            zos.putNextEntry(new ZipEntry(fileName));
//                            byte[] bytes = new byte[PKIFailureInfo.badRecipientNonce];
//                            while (true) {
//                                int length = bis2.read(bytes);
//                                if (length < 0) {
//                                    break;
//                                }
//                                zos.write(bytes, 0, length);
//                            }
//                            bis = bis2;
//                            fis = fis2;
//                        } catch (ZipException e2) {
//                            e2.printStackTrace();
//                            bis = bis2;
//                            fis = fis2;
//                        }
//                    } catch (Exception e3) {
//                        e = e3;
//                        bis = bis2;
//                        fis = fis2;
//                        try {
//                            throw e;
//                        } catch (Throwable th2) {
//                            th = th2;
//                        }
//                    } catch (Throwable th3) {
//                        th = th3;
//                        bis = bis2;
//                        fis = fis2;
//                        if (zos != null) {
//                            try {
//                                zos.closeEntry();
//                            } catch (IOException e4) {
//                                e4.printStackTrace();
//                            }
//                        }
//                        if (fis != null) {
//                            try {
//                                fis.close();
//                            } catch (IOException e42) {
//                                e42.printStackTrace();
//                            }
//                        }
//                        if (bis != null) {
//                            try {
//                                bis.close();
//                            } catch (IOException e422) {
//                                e422.printStackTrace();
//                            }
//                        }
//                        throw th;
//                    }
//                } catch (Exception e5) {
//                    e = e5;
//                    fis = fis2;
//                    throw e;
//                } catch (Throwable th4) {
//                    th = th4;
//                    fis = fis2;
//                    if (zos != null) {
//                        zos.closeEntry();
//                    }
//                    if (fis != null) {
//                        fis.close();
//                    }
//                    if (bis != null) {
//                        bis.close();
//                    }
//                    //throw th;
//                }
//            }
//            if (zos != null) {
//                try {
//                    zos.closeEntry();
//                } catch (IOException e4222) {
//                    e4222.printStackTrace();
//                }
//            }
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e42222) {
//                    e42222.printStackTrace();
//                }
//            }
//            if (bis != null) {
//                try {
//                    bis.close();
//                } catch (IOException e422222) {
//                    e422222.printStackTrace();
//                }
//            }
//        } catch (Exception e6) {
//            e = e6;
//            throw e;
//        }
//    }

//    public static void unzipFromAsset(Context context, String zipFile, String destDir) throws Exception {
//        Exception e;
//        Throwable th;
//        byte[] buff = new byte[PKIFailureInfo.badRecipientNonce];
//        if (!destDir.endsWith(File.separator)) {
//            destDir = destDir + File.separator;
//        }
//        File destDirFile = new File(destDir);
//        if (!destDirFile.exists()) {
//            destDirFile.mkdirs();
//        }
//        BufferedInputStream bis = null;
//        ZipInputStream zis = null;
//        try {
//            BufferedInputStream bis2 = new BufferedInputStream(context.getAssets().open(zipFile));
//            try {
//                ZipInputStream zis2 = new ZipInputStream(bis2);
//                while (true) {
//                    try {
//                        ZipEntry zipEntry = zis2.getNextEntry();
//                        if (zipEntry == null) {
//                            break;
//                        }
//                        File unZipFile = new File(destDir + zipEntry.getName());
//                        if (!zipEntry.isDirectory()) {
//                            File parentDir = unZipFile.getParentFile();
//                            if (!(parentDir == null || parentDir.isDirectory())) {
//                                parentDir.mkdirs();
//                            }
//                            FileOutputStream fos = new FileOutputStream(unZipFile, false);
//                            while (true) {
//                                try {
//                                    int readSize = zis2.read(buff, 0, buff.length);
//                                    if (readSize <= 0) {
//                                        break;
//                                    }
//                                    fos.write(buff, 0, readSize);
//                                } catch (Exception e2) {
//                                    e2.printStackTrace();
//                                    if (fos != null) {
//                                        fos.close();
//                                    } else {
//                                        continue;
//                                    }
//                                } catch (Throwable th2) {
//                                    if (fos != null) {
//                                        fos.close();
//                                    }
//                                }
//                            }
//                            zis2.closeEntry();
//                            fos.flush();
//                            if (fos != null) {
//                                fos.close();
//                            } else {
//                                continue;
//                            }
//                        } else if (!unZipFile.isDirectory()) {
//                            unZipFile.mkdirs();
//                        }
//                    } catch (Exception e3) {
//                       // e2 = e3;
//                        zis = zis2;
//                        bis = bis2;
//                    } catch (Throwable th3) {
//                        th = th3;
//                        zis = zis2;
//                        bis = bis2;
//                    }
//                }
//                if (bis2 != null) {
//                    try {
//                        bis2.close();
//                    } catch (IOException e4) {
//                        e4.printStackTrace();
//                    }
//                }
//                if (zis2 != null) {
//                    try {
//                        zis2.close();
//                    } catch (IOException e42) {
//                        e42.printStackTrace();
//                    }
//                }
//            } catch (Exception e5) {
//               // e2 = e5;
//                bis = bis2;
//                try {
//                    //throw e2;
//                } catch (Throwable th4) {
//                    th = th4;
//                }
//            } catch (Throwable th5) {
//                th = th5;
//                bis = bis2;
//                if (bis != null) {
//                    try {
//                        bis.close();
//                    } catch (IOException e422) {
//                        e422.printStackTrace();
//                    }
//                }
//                if (zis != null) {
//                    try {
//                        zis.close();
//                    } catch (IOException e4222) {
//                        e4222.printStackTrace();
//                    }
//                }
//               // throw th;
//            }
//        } catch (Exception e6) {
//            //e2 = e6;
//           // throw e2;
//        }
//    }

//    public static void unzipFromFile(String srcPath, String destDir) {
//        FileNotFoundException e;
//        IOException e2;
//        Throwable th;
//        byte[] buff = new byte[PKIFailureInfo.badRecipientNonce];
//        if (!destDir.endsWith(File.separator)) {
//            destDir = destDir + File.separator;
//        }
//        File destDirFile = new File(destDir);
//        if (!destDirFile.exists()) {
//            destDirFile.mkdirs();
//        }
//        BufferedInputStream bufferedInputStream = null;
//        ZipInputStream zis = null;
//        try {
//            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(srcPath)));
//           // try {
//                ZipInputStream zis2 = new ZipInputStream(bis);
//                while (true) {
//                    try {
//                        ZipEntry zipEntry = zis2.getNextEntry();
//                        if (zipEntry == null) {
//                            break;
//                        }
//                        File unZipFile = new File(destDir + zipEntry.getName());
//                        if (!zipEntry.isDirectory()) {
//                            File parentDir = unZipFile.getParentFile();
//                            if (!(parentDir == null || parentDir.isDirectory())) {
//                                parentDir.mkdirs();
//                            }
//                            FileOutputStream fos = new FileOutputStream(unZipFile, false);
//                            while (true) {
//                                try {
//                                    int readSize = zis2.read(buff, 0, buff.length);
//                                    if (readSize <= 0) {
//                                        break;
//                                    }
//                                    fos.write(buff, 0, readSize);
//                                } catch (Exception e3) {
//                                    e3.printStackTrace();
//                                    if (fos != null) {
//                                        fos.close();
//                                    } else {
//                                        continue;
//                                    }
//                                } catch (Throwable th2) {
//                                    if (fos != null) {
//                                        fos.close();
//                                    }
//                                }
//                            }
//                            zis2.closeEntry();
//                            fos.flush();
//                            if (fos != null) {
//                                fos.close();
//                            } else {
//                                continue;
//                            }
//                        } else if (!unZipFile.isDirectory()) {
//                            unZipFile.mkdirs();
//                        }
//                    } catch (FileNotFoundException e4) {
//                        e = e4;
//                        zis = zis2;
//                        bufferedInputStream = bis;
//                    } catch (IOException e5) {
//                        e2 = e5;
//                        zis = zis2;
//                        bufferedInputStream = bis;
//                    } catch (Throwable th3) {
//                        th = th3;
//                        zis = zis2;
//                        bufferedInputStream = bis;
//                    }
//                }
//                if (bis != null) {
//                    try {
//                        bis.close();
//                    } catch (IOException e22) {
//                        e22.printStackTrace();
//                    }
//                }
//                if (zis2 != null) {
//                    try {
//                        zis2.close();
//                        zis = zis2;
//                        bufferedInputStream = bis;
//                        return;
//                    } catch (IOException e222) {
//                        e222.printStackTrace();
//                        zis = zis2;
//                        bufferedInputStream = bis;
//                        return;
//                    }
//                }
//                bufferedInputStream = bis;
//            //} //catch (FileNotFoundException e6) {
//                //e = e6;
//                bufferedInputStream = bis;
//                try {
//                   // e.printStackTrace();
//                    if (bufferedInputStream != null) {
//                        try {
//                            bufferedInputStream.close();
//                        } catch (IOException e2222) {
//                            e2222.printStackTrace();
//                        }
//                    }
//                    if (zis != null) {
//                        try {
//                            zis.close();
//                        } catch (IOException e22222) {
//                            e22222.printStackTrace();
//                        }
//                    }
//                } catch (Throwable th4) {
//                    th = th4;
//                    if (bufferedInputStream != null) {
//                        try {
//                            bufferedInputStream.close();
//                        } catch (IOException e222222) {
//                            e222222.printStackTrace();
//                        }
//                    }
//                    if (zis != null) {
//                        try {
//                            zis.close();
//                        } catch (IOException e2222222) {
//                            e2222222.printStackTrace();
//                        }
//                    }
//                   // throw th;
//                }
//           // } catch (IOException e7) {
//               // e2222222 = e7;
//                bufferedInputStream = bis;
//                //e2222222.printStackTrace();
//                if (bufferedInputStream != null) {
//                    try {
//                        bufferedInputStream.close();
//                    } catch (IOException e22222222) {
//                        e22222222.printStackTrace();
//                    }
//                }
//                if (zis != null) {
//                    try {
//                        zis.close();
//                    } catch (IOException e222222222) {
//                        e222222222.printStackTrace();
//                    }
//                }
//            } catch (Throwable th5) {
//                th = th5;
//                bufferedInputStream = bis;
//                if (bufferedInputStream != null) {
//                    bufferedInputStream.close();
//                }
//                if (zis != null) {
//                    zis.close();
//                }
//                //throw th;
//            }
//        //} catch (FileNotFoundException e8) {
////            e = e8;
////            e.printStackTrace();
////            if (bufferedInputStream != null) {
////               // bufferedInputStream.close();
////            }
////            if (zis != null) {
////               // zis.close();
////            }
//       // } catch (IOException e9) {
////            e222222222 = e9;
////            e222222222.printStackTrace();
////            if (bufferedInputStream != null) {
////                bufferedInputStream.close();
////            }
////            if (zis != null) {
////                zis.close();
////            }
//        }
//    }
}
