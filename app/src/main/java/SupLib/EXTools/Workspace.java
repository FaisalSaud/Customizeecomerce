package SupLib.EXTools;

import android.content.Context;
import android.content.pm.PackageInfo;

import SupLib.EXTools.FileUtil;
import SupLib.EXTools.MapUtil;
import SupLib.EXTools.ZipUtil;

import SupLib.EXTools.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Workspace {
    private final String TEMPLATE_ZIP_NAME = "template.zip";
    public String androidJar;
    public String apkName;
    public String appName;
    public String appVerCode;
    public String appVerName;
    public String applicationJava;
    public String binDir;
    public String classesDir;
    private Context context;
    public ArrayList<String> excepFileList;
    FileUtil fileUtil;
    public ArrayList<String> fullscreenFileList;
    public String genDir;
    public String launcherClassName;
    public String mainJava;
    public String manifestXml;
    public String outApk;
    public String outApkRes;
    public String outApkUnsigned;
    public String outDex;
    public String pkgName;
    public String pkgPath;
    public String rawDir;
    public String resDir;
    public String resLayoutDir;
    public String srcDir;
    public String srcMainDir;
    public String uploadZip;
    public String wsHome;

    public Workspace(Context context, String wsHome, Map<String, Object> myScMap) {
        this.context = context;
        this.wsHome = wsHome;
        this.pkgName = MapUtil.getString(myScMap, "my_sc_pkg_name");
        this.apkName = MapUtil.getString(myScMap, "my_ws_name");
        this.appName = MapUtil.getString(myScMap, "my_app_name");
        this.appVerCode = MapUtil.getString(myScMap, "sc_ver_code");
        this.appVerName = MapUtil.getString(myScMap, "sc_ver_name");
        init();
    }

    private void init() {
        this.fileUtil = new FileUtil(true);
        this.excepFileList = new ArrayList();
        this.excepFileList.add(Defines.FILE_JAVA_APPLICATION);
        this.excepFileList.add(Defines.FILE_JAVA_DEBUG_ACTIVITY);
        if (!this.wsHome.endsWith(File.separator)) {
            this.wsHome += File.separator;
        }
        this.fullscreenFileList = new ArrayList();
        this.pkgPath = this.pkgName.replaceAll("\\.", File.separator);
        this.binDir = this.wsHome + "bin";
        this.classesDir = this.binDir + File.separator + "classes";
        this.genDir = this.wsHome + "gen";
        this.srcMainDir = this.wsHome + "app" + File.separator + "src" + File.separator + "main";
        this.srcDir = this.srcMainDir + File.separator + "java";
        this.resDir = this.srcMainDir + File.separator + "res";
        this.resLayoutDir = this.resDir + File.separator + "layout";
        this.rawDir = this.resDir + File.separator + "raw";
        this.manifestXml = this.wsHome + "app" + File.separator + "src" + File.separator + "main" + File.separator + Defines.FILE_XML_MANIFEST;
        this.androidJar = Defines.getSketchHomeHome() + File.separator + "android.jar";
        this.outApkRes = this.binDir + File.separator + this.apkName + ".apk.res";
        this.outDex = this.binDir + File.separator + "classes.dex";
        this.mainJava = this.srcDir + File.separator + this.pkgPath;
        this.mainJava += File.separator + Defines.FILE_JAVA_MAIN;
        this.applicationJava = this.srcDir + File.separator + this.pkgPath;
        this.applicationJava += File.separator + Defines.FILE_JAVA_APPLICATION;
        this.outApkUnsigned = this.binDir + File.separator + this.apkName + ".apk.unsigned";
        this.outApk = this.binDir + File.separator + this.apkName + ".apk";
    }

    public void cleanWorkspace() {
        this.fileUtil.deleteDir(this.binDir, false);
        this.fileUtil.deleteDir(this.classesDir, false);
        this.fileUtil.deleteDir(this.genDir, false);
        this.fileUtil.deleteDir(this.srcDir, false);
        this.fileUtil.deleteDir(this.resDir, false);
        this.fileUtil.deleteDir(this.rawDir, false);
    }

    public void initWorkspace() throws Exception {
        this.fileUtil.makeDir(this.binDir);
        this.fileUtil.makeDir(this.classesDir);
        this.fileUtil.makeDir(this.genDir);
        this.fileUtil.makeDir(this.srcDir);
        this.fileUtil.makeDir(this.resDir);
        this.fileUtil.makeDir(this.resLayoutDir);
        this.fileUtil.makeDir(this.rawDir);
        this.fullscreenFileList.clear();
//        unzipTemplate();
    }

    public void unzipRes(String resPath) {
        try {
            //ZipUtil.unzipFromAsset(this.context, resPath, this.resDir);
        } catch (Exception e) {
        }
    }

//    public void copyIcon(String iconPath) {
//        try {
//            this.fileUtil.copyFile(iconPath, this.resDir + File.separator + "drawable-xhdpi" + File.separator + "app_icon.png");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public int getApkVersionCode() {
        PackageInfo packageInfo = this.context.getPackageManager().getPackageArchiveInfo(this.outApk, 0);
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return 0;
    }

//    public void copyImages(ArrayList<String> imagePath) {
//        Iterator it = imagePath.iterator();
//        while (it.hasNext()) {
//            String path = (String) it.next();
//            try {
//                this.fileUtil.copyFile(path, this.resDir + File.separator + "drawable-xhdpi" + File.separator + path.substring(path.lastIndexOf(File.separator) + 1));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public void unzipTemplate() throws Exception {
//        ZipUtil.unzipFromAsset(this.context, "template.zip", this.wsHome);
//        this.fileUtil.writeFile(this.srcDir + File.separator + this.pkgPath + File.separator + Defines.FILE_JAVA_DEBUG_ACTIVITY, this.fileUtil.readFileFromAsset(this.context, "debug" + File.separator + Defines.FILE_JAVA_DEBUG_ACTIVITY).replaceAll("\\<\\?package_name\\?\\>", this.pkgName));
//        this.fileUtil.writeFile(this.srcDir + File.separator + this.pkgPath + File.separator + Defines.FILE_JAVA_APPLICATION, this.fileUtil.readFileFromAsset(this.context, "debug" + File.separator + Defines.FILE_JAVA_APPLICATION).replaceAll("\\<\\?package_name\\?\\>", this.pkgName));
//        String gradle = this.wsHome + File.separator + "app" + File.separator + "build.gradle";
//        this.fileUtil.writeFile(gradle, this.fileUtil.readFile(gradle).replaceAll("\\<\\?package_name\\?\\>", this.pkgName));
//    }

    public void deleteRuntimeFile() {
        this.fileUtil.deleteDir(this.binDir);
        this.fileUtil.deleteDir(this.genDir);
    }

    public void createRuntimeFile() {
        this.fileUtil.makeDir(this.binDir);
        this.fileUtil.makeDir(this.classesDir);
        this.fileUtil.makeDir(this.genDir);
    }

    public boolean existApkFile() {
        return new File(this.outApk).exists();
    }

    public String dumpManifestXml() {
        String xml = BuildConfig.VERSION_NAME;
        StringBuffer sb = new StringBuffer();
        try {
            xml = this.fileUtil.readFile(this.manifestXml);
            for (String line : xml.split("\n")) {
                if (!(line.contains(Defines.FILE_JAVA_APPLICATION.replaceAll(".java", BuildConfig.VERSION_NAME)) || line.contains(Defines.FILE_JAVA_DEBUG_ACTIVITY.replaceAll(".java", BuildConfig.VERSION_NAME)))) {
                    sb.append(line).append("\n");
                }
            }
            this.fileUtil.writeFile(this.manifestXml, sb.toString());
        } catch (IOException e) {
        }
        return xml;
    }

    public void saveFile(String srcFileName, String source) throws IOException {
        if (srcFileName.endsWith("java")) {
            this.fileUtil.writeFile(this.srcDir + File.separator + this.pkgPath + File.separator + srcFileName, source);
            if (!this.fullscreenFileList.contains(this.srcMainDir + File.separator + "java" + File.separator + this.pkgPath + File.separator + srcFileName)) {
                this.fullscreenFileList.add(this.srcMainDir + File.separator + "java" + File.separator + this.pkgPath + File.separator + srcFileName);
            }
        } else if (srcFileName.equals(Defines.FILE_XML_MANIFEST)) {
            this.fileUtil.writeFile(this.manifestXml, source);
            if (!this.fullscreenFileList.contains(this.srcMainDir + File.separator + srcFileName)) {
                this.fullscreenFileList.add(this.srcMainDir + File.separator + srcFileName);
            }
        } else {
            this.fileUtil.writeFile(this.resLayoutDir + File.separator + srcFileName, source);
            if (!this.fullscreenFileList.contains(this.resLayoutDir + File.separator + srcFileName)) {
                this.fullscreenFileList.add(this.resLayoutDir + File.separator + srcFileName);
            }
        }
    }
}
