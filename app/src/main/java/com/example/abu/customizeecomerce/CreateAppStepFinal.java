package com.example.abu.customizeecomerce;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import SupportClasses.ManifestModifier;

public class CreateAppStepFinal extends AppCompatActivity implements ApkMakingInterface {
    boolean cancel = false;
    String Appname;
    Drawable Appicon;

    MakingThread MK;
    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        getWindow().requestFeature(1);
        setContentView(R.layout.activity_create_app_step_final);
        try {
            //TextView TVtest = (TextView) findViewById(R.id.textView43);
            //String Stest = new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath())).append("/").append("CreatedFromMyStore").append("/").append(CreateAppStep1.storeName.getText().toString()).toString();
            //TVtest.setText(Stest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buildButtonStep5) {
            getAppname();
            createUserApk();
        }
    }
    public String getSourceAssetFile() {
        return "template3";
    }

    public String getTargetApkName() {
        return "LiveWallpaper.apk";
    }
    private String getWorkingDirectory() {
        String workingDirPath = new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath())).append("/").append("CreatedFromMyStore").toString();
        File dirFile = new File(workingDirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return workingDirPath;
    }

    private void getAppname(){
        Appicon = CreateAppStep1.img.getBackground();
        Appname = CreateAppStep1.storeNameS;
    }

    public Map<String, String> generateReplaces() {
        String filePath;
        Map<String, String> replaces = new HashMap<>();
        String workingDirPath = getWorkingDirectory();
        try {
            String resFilePath = new StringBuilder(String.valueOf(workingDirPath)).append("/resources.arsc").toString();
            FileOutputStream fos = new FileOutputStream(resFilePath);
            InputStream input = getAssets().open("resources.3");
            LinkedHashMap<Integer, String> offsetStrings = new LinkedHashMap();
            offsetStrings.put(Integer.valueOf(693), CreateAppStep1.storeNameS);
            this.prepareResourceFile(input, fos, offsetStrings);
            replaces.put("resources.arsc", resFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos;
        try {
            filePath = new StringBuilder(String.valueOf(workingDirPath)).append("/icon.png").toString();
            fos = new FileOutputStream(filePath);
//            input = getResources().openRawResource(R.id.storeIcon);
//            IOUtils.copy(input, fos);
//            input.close();
            fos.close();
           // replaces.put("res/drawable-hdpi/ic_launcher.png", filePath);
        } catch (Exception e2) {
            e2.printStackTrace();
        }

        try {
            String manifestPath = new StringBuilder(String.valueOf(workingDirPath)).append("/AndroidManifest.xml").toString();
            new ManifestModifier(getAssets().open("AndroidManifest.3"), manifestPath).renameString("com.gmail.heagoo.livewallpaper", "BackageNameOfUser");
            replaces.put("AndroidManifest.xml", manifestPath);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return replaces;
    }

    public Map<String, String> generateAssetFiles() {
        return null;
    }

    protected static void prepareResourceFile(InputStream input, FileOutputStream fos, LinkedHashMap<Integer, String> offsetStrings) throws IOException {
        byte[] buffer;
        int position = 0;
        for (Map.Entry<Integer, String> entity : offsetStrings.entrySet()) {
            int offset = ((Integer) entity.getKey()).intValue();
            String newVal = (String) entity.getValue();
            if (offset > position) {
                buffer = new byte[(offset - position)];
                input.read(buffer);
                position += offset - position;
                fos.write(buffer);
            }
            byte[] nameBytes = new byte[64];
            byte[] encodedName = newVal.getBytes("utf-8");
            while (encodedName.length > 64) {
                newVal = newVal.substring(0, newVal.length() - 1);
                encodedName = newVal.getBytes("utf-8");
            }
            System.arraycopy(encodedName, 0, nameBytes, 0, encodedName.length);
            fos.write(nameBytes);
            input.skip(64);
            position += 64;
        }
        buffer = new byte[4096];
        while (true) {
            int read = input.read(buffer);
            if (read <= 0) {
                input.close();
                fos.close();
                return;
            }
            fos.write(buffer, 0, read);
        }
    }
    private void createUserApk() {
        MakingThread thread = new MakingThread(this, this);
        thread.start();
        Toast.makeText(CreateAppStepFinal.this,"app made",Toast.LENGTH_LONG).show();
    }
    public void onFinish(boolean result) {
    }
}
