package SupLib.build;
import SupLib.EXTools.Workspace;
import SupLib.sdklib.build.ApkBuilder;

import java.io.File;

public class ApkMaker {
    public void build(Workspace workspace) throws Exception {
        File outApk = new File(workspace.outApkUnsigned);
        File resFile = new File(workspace.outApkRes);
        File dexFile = new File(workspace.outDex);
        File srcDir = new File(workspace.srcDir);
        ApkBuilder apkBuilder = new ApkBuilder(outApk, resFile, dexFile, null, null, System.out);
        apkBuilder.setDebugMode(true);
        apkBuilder.sealApk();
    }

//    public boolean singApk(Workspace workspace) throws GeneralSecurityException, ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {
//        ZipSigner zipSigner = new ZipSigner();
//        zipSigner.addAutoKeyObserver(new Observer() {
//            public void update(Observable observable, Object data) {
//            }
//        });
//        KeyStoreFileManager.setProvider(new BouncyCastleProvider());
//        zipSigner.setKeymode(ZipSigner.KEY_TESTKEY);
//        zipSigner.signZip(workspace.outApkUnsigned, workspace.outApk);
//        return true;
//    }
}
