package com.example.abu.customizeecomerce;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import SupportClasses.RandUtil;

public class MakingThread extends Thread {
    private Context context;
    private String errMessage;
    private boolean finished = false;
    private String inputApkPath;
    private Map<String, String> jarPath2FilePath;
    private ApkMakingInterface makingInterface;
    private String outputApkPath;
    private boolean succeed = false;
    private String workingDirPath;

    public MakingThread(Context ctx, ApkMakingInterface makingInterface ) {
        this.context = ctx;
        this.makingInterface = makingInterface;
        if (Environment.getExternalStorageState().equals("mounted")) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            this.workingDirPath = new StringBuilder(String.valueOf(path)).append("/").append("CreatedFromMyStore").append("/").append(RandUtil.getRandomString(4)).toString();
            File f = new File(this.workingDirPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            this.inputApkPath = this.workingDirPath + "/apk";
            this.outputApkPath = new StringBuilder(String.valueOf(path)).append("/").append("CreatedFromMyStore").append("/").append(CreateAppStep1.storeName.getText().toString()).toString();
            return;
        }
        showTip("Can not find SD card!");
    }

    public String getErrorMessage() {
        return this.errMessage;
    }

    private void showTip(String tip) {
        Toast.makeText(this.context, tip, Toast.LENGTH_SHORT).show();
    }

    public void run() {
        try {
            String clsName;
            long startTime = System.currentTimeMillis();
            AssetManager am = this.context.getAssets();
            this.jarPath2FilePath = this.makingInterface.generateReplaces();
            copyStream(am.open(this.makingInterface.getSourceAssetFile()), new FileOutputStream(this.inputApkPath));
            //String keyName = SettingActivity.getSignKeyName(this.context);
            //SignApk.signAPK(am.open(new StringBuilder(String.valueOf(keyName)).append(".x509.pem").toString()), am.open(new StringBuilder(String.valueOf(keyName)).append(".pk8").toString()), this.inputApkPath, this.outputApkPath, this.jarPath2FilePath, assetFiles, 9);
        this.succeed = true;
        if (this.succeed) {
            long runningTime = System.currentTimeMillis() - startTime;
            if (runningTime < 5000) {
                try {
                    Thread.sleep(5000 - runningTime);
                } catch (InterruptedException e2) {
                }
            }
        }
        this.finished = true;
    } catch (Exception e){}
    }

    private void copyStream(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[8192];
        while (true) {
            int count = is.read(buffer);
            if (count > 0) {
                os.write(buffer, 0, count);
            } else {
                return;
            }
        }
    }

    public boolean isFinished() {
        return this.finished;
    }

    public boolean isSucceed() {
        return this.succeed;
    }
}
