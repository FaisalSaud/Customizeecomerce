package com.example.abu.customizeecomerce;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

//import SupLib.EXTools.Workspace;
//import SupLib.SDK.xml.AndroidManifest;
//import SupLib.build.Aapt;
//import SupLib.build.ApkMaker;
//import SupLib.build.Dx;
//import SupLib.build.JavaCompile;

public class CreateAppStepFinal extends AppCompatActivity {
    boolean cancel = false;
//    public static Workspace workspace;
//    Aapt aapt = new Aapt();
    Context context = new ContextWrapper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step_final);
        try {
            actionTask();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void actionTask() throws Exception {
        Intent intent;
        if (!this.cancel) {
            try {
////                CreateAppStepFinal.saveAllSource();
////                int curVersion = SysUtil.getSketchVersion(this.context);
////                int preVersion = CreateAppStepFinal.this.prefInstall.getInt(Defines.PREF_ITEM_INSTALL_VERSION, 0);
////                if (CreateAppStepFinal.this.prefInstall.getString(Defines.PREF_ITEM_INSTALL_AAPT).isEmpty() || curVersion > preVersion) {
//                    // check 1
//                    publishProgress(new String[]{CreateAppStepFinal.this.getString(R.string.desc_ready_build)});
//                    Toast.makeText(CreateAppStepFinal.this , "1" ,Toast.LENGTH_LONG).show();
//                    CreateAppStepFinal.this.aapt.init(this.context);
//                    CreateAppStepFinal.this.prefInstall.putObject(Defines.PREF_ITEM_INSTALL_AAPT, CreateAppStepFinal.this.aapt.getAaptPath());
//                    CreateAppStepFinal.this.aapt.setAaptPath(CreateAppStepFinal.this.prefInstall.getString(Defines.PREF_ITEM_INSTALL_AAPT));
//                }
//                String androidJar = Defines.getSketchHomeHome() + File.separator + "android.jar";
//                if (!new File(androidJar).exists()) {
//                    // check 2
//                    publishProgress(new String[]{CreateAppStepFinal.this.getString(R.string.desc_ready_build)});
//                    Toast.makeText(CreateAppStepFinal.this , "2" ,Toast.LENGTH_LONG).show();
//                    CreateAppStepFinal.this.fileUtil.copyFileFromAsset(this.context, "android.jar", androidJar);
//                    CreateAppStepFinal.this.prefInstall.putObject(Defines.PREF_ITEM_INSTALL_ANDROID_JAR, Boolean.valueOf(true));
//                }
                // check 3 (aapt)
               // publishProgress(new String[]{"Aapt is running..."});
                Toast.makeText(CreateAppStepFinal.this , "Aapt" ,Toast.LENGTH_LONG).show();
//                CreateAppStepFinal.this.aapt.execute(CreateAppStepFinal.workspace);
                if (!this.cancel) {
                    // check 4 (java)
                  //  publishProgress(new String[]{"Java is compiling..."});
                    Toast.makeText(CreateAppStepFinal.this , "java" ,Toast.LENGTH_LONG).show();
//                    new JavaCompile().compile(CreateAppStepFinal.workspace);
                    if (!this.cancel) {
                        // check 5 (dx)
                        //publishProgress(new String[]{"Dx is running..."});
                        Toast.makeText(CreateAppStepFinal.this , "Dx" ,Toast.LENGTH_LONG).show();
//                        new Dx().dx(CreateAppStepFinal.workspace);
                        // check 6 (apk B)
                        //publishProgress(new String[]{"Apk building..."});
                        Toast.makeText(CreateAppStepFinal.this , "Apk B" ,Toast.LENGTH_LONG).show();
//                        ApkMaker apkMaker = new ApkMaker();
//                        apkMaker.build(CreateAppStepFinal.workspace);
                        if (!this.cancel) {
//                            boolean apkResult = apkMaker.singApk(CreateAppStepFinal.workspace);
                            if (!this.cancel) {
                                // check 7 (apk R)
                               // publishProgress(new String[]{"Apk ready."});
                                Toast.makeText(CreateAppStepFinal.this , "Apk R" ,Toast.LENGTH_LONG).show();
                                if (!this.cancel) {
                                    CreateAppStepFinal.this.scInstall();
                                    //CreateAppStepFinal.this.btnExecute.setEnabled(true);
                                }
                            }
                        }
                    }
                }
            } catch(Exception e){
                Toast.makeText(CreateAppStepFinal.this,"compile error",Toast.LENGTH_SHORT);
            }
//            catch (SketchCompileException e) {
//                CreateAppStepFinal.this.showCompileErrorMessage(e.getMessage());
//            } catch (SketchBuildException e2) {
//                e2.printStackTrace();
//                intent = new Intent(this.context, CollectErrorActivity.class);
//                intent.putExtra(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, e2.getMessage());
//                intent.setFlags(PKIFailureInfo.duplicateCertReq);
//                CreateAppStepFinal.this.startActivity(intent);
//            } catch (Exception e3) {
//                intent = new Intent(this.context, CollectErrorActivity.class);
//                intent.putExtra(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE, new StringUtil().printStackTraceToString(e3));
//                intent.setFlags(PKIFailureInfo.duplicateCertReq);
//                CreateAppStepFinal.this.startActivity(intent);
//            }
        }

    }
    private void scInstall() {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            Uri uri =null;// FileProvider.getUriForFile(this.context, this.context.getApplicationContext().getPackageName() + ".provider", new File(workspace.outApk));
            intent.addFlags(1);
            intent.addFlags(2);
            intent.addFlags(64);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        } else {
           // intent.setDataAndType(Uri.fromFile(new File(workspace.outApk)), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }
//    protected void saveAllSource() throws Exception {
//        if (isDownload) {
//            workspace.unzipRes(Defines.getResZipPath(ScDefine.CID_CUSTOM_EDIT));
//            workspace.copyIcon(Defines.getDownloadDataHome() + File.separator + "icon.png");
//            resourceManager.copyImages(workspace.resDir + File.separator + "drawable-xhdpi");
//            resourceManager.copySounds(workspace.resDir + File.separator + "raw");
//        } else {
//            workspace.unzipRes(Defines.getResZipPath(scId));
//        }
//        workspace.fullscreenFileList.clear();
//        boolean needPermissionCall = false;
//        boolean needPermissionVibrator = false;
//        Iterator it = projectFileManager.javaFileList.iterator();
//        while (it.hasNext()) {
//            String java = (String) it.next();
//            JavaSourceMaker javaMaker = new JavaSourceMaker(getApplicationContext(), scId, workspace);
//            javaMaker.setActivityName(java);
//            javaMaker.setVariablesForViews(designDataManager.getAllViews(ProjectFileManager.getXmlNameFromJava(java)));
//            javaMaker.setVariablesForLogics(designDataManager.getVariables(java));
//            javaMaker.setListsForLogics(designDataManager.getLists(java));
//            javaMaker.setInitViews(designDataManager.getAllViews(ProjectFileManager.getXmlNameFromJava(java)));
//            javaMaker.setInitComponents(designDataManager.getComponents(java));
//            javaMaker.setInitLogic(designDataManager.getBlocks(java, "onCreate_initializeLogic"));
//            javaMaker.setVariableForComponents(designDataManager.getComponents(java));
//            javaMaker.setInitEvents(designDataManager.getEvents(java), designDataManager.getAllBlocks(java));
//            javaMaker.setFunctions(designDataManager.getFunctions(java), designDataManager.getAllBlocks(java));
//            javaMaker.setDefinedFunctions(java);
//            javaMaker.saveSource(java);
//            for (Map.Entry<String, ArrayList<BlockBean>> entry : designDataManager.getAllBlocks(java).entrySet()) {
//                Iterator it2 = ((ArrayList) entry.getValue()).iterator();
//                while (it2.hasNext()) {
//                    BlockBean bean = (BlockBean) it2.next();
//                    if (bean.opCode.equals(DefineBlock.BLOCK_OPCODE_INTENT_SET_ACTION) && ((String) bean.parameters.get(1)).equals(DefineSource.INTENT_ACTION_COMMON[1])) {
//                        needPermissionCall = true;
//                    } else if (bean.opCode.equals(DefineBlock.BLOCK_OPCODE_VIBRATOR_ACTION)) {
//                        needPermissionVibrator = true;
//                    }
//                }
//            }
//        }
//        Iterator it3 = projectFileManager.xmlFileList.iterator();
//        while (it3.hasNext()) {
//            String xml = (String) it3.next();
//            XmlSourceMaker xmlMaker = new XmlSourceMaker(getApplicationContext(), scId, workspace);
//            xmlMaker.makeSource(designDataManager.reorderViewBeans(designDataManager.getAllViews(xml)));
//            xmlMaker.saveSource(xml);
//        }
//        ManifestSourceMaker manifest = new ManifestSourceMaker(getApplicationContext(), scId, workspace);
//        if (needPermissionCall) {
//            manifest.addPermission(0);
//        }
//        if (needPermissionVibrator) {
//            manifest.addPermission(2);
//        }
//        it3 = projectFileManager.javaFileList.iterator();
//        while (it3.hasNext()) {
//            java = (String) it3.next();
//            if (!java.equals(Defines.FILE_JAVA_MAIN)) {
//                manifest.addActivity(java.substring(0, java.indexOf(".java")));
//            }
//        }
//        manifest.saveSource();
//    }
}
