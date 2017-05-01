package SupLib.build;

import android.content.Context;

//import com.besome.sketch.define.DefineXml;
import SupLib.EXTools.Workspace;
//import com.besome.sketch.exp.SketchBuildException;
//import com.besome.sketch.exp.SketchException;
//import com.besome.sketch.lib.utils.CommandLineRunner;
import SupLib.EXTools.FileUtil;
//import com.besome.sketch.lib.utils.LogCatGrabber;
//import com.besome.sketch.lib.utils.SysUtil;

import java.io.File;
import java.util.ArrayList;

public class Aapt {
    File aaptFile;
    ArrayList<String> argsList = new ArrayList();
    CommandLineRunner cmdRunner = new CommandLineRunner();
    LogCatGrabber logcat = new LogCatGrabber();

    public void init(Context context) throws Exception {
        String arch = SysUtil.getAbi();
        if (arch == null) {
            throw new SketchException("Can not read cpu abi information");
        }
        String aaptName;
        if (arch.toLowerCase().matches("mip")) {
            aaptName = "aapt/aapt-mips";
        } else if (arch.toLowerCase().matches("x86")) {
            aaptName = "aapt/aapt-x86-pie";
        } else {
            aaptName = "aapt/aapt-arm-pie";
        }
        File aaptLoc = new File(context.getFilesDir(), "tmp");
        if (!aaptLoc.exists()) {
            aaptLoc.mkdir();
        }
        this.aaptFile = new File(aaptLoc, "aapt");
        new FileUtil().copyFileFromAsset(context, aaptName, this.aaptFile.getAbsolutePath());
        this.argsList.clear();
        this.argsList.add("chmod");
        this.argsList.add("744");
        this.argsList.add(this.aaptFile.getAbsolutePath());
        if (commandRun(this.argsList) != 0) {
        }
    }

    public String getAaptPath() {
        if (this.aaptFile.getAbsolutePath() == null) {
            return BuildConfig.VERSION_NAME;
        }
        return this.aaptFile.getAbsolutePath();
    }

    public void setAaptPath(String path) {
        this.aaptFile = new File(path);
    }

    public int commandRun(ArrayList<String> argsList) {
        int result = this.cmdRunner.cmdLineRun((String[]) argsList.toArray(new String[0]), this.logcat);
        return result == 0 ? result : result;
    }

    public int execute(Workspace workspace) throws Exception {
        this.argsList.clear();
        this.argsList.add(this.aaptFile.getAbsolutePath());
        this.argsList.add(DefineXml.TAG_KEY_PACKAGE);
        this.argsList.add("--min-sdk-version");
        this.argsList.add("19");
        this.argsList.add("--target-sdk-version");
        this.argsList.add("22");
        this.argsList.add("--version-code");
        ArrayList arrayList = this.argsList;
        Object obj = (workspace.appVerCode == null || workspace.appVerCode.isEmpty()) ? AppEventsConstants.EVENT_PARAM_VALUE_YES : workspace.appVerCode;
        arrayList.add(obj);
        this.argsList.add("--version-name");
        arrayList = this.argsList;
        obj = (workspace.appVerName == null || workspace.appVerName.isEmpty()) ? "1.0" : workspace.appVerName;
        arrayList.add(obj);
        this.argsList.add("--auto-add-overlay");
        this.argsList.add("-v");
        this.argsList.add("-f");
        this.argsList.add("--no-crunch");
        this.argsList.add("-m");
        this.argsList.add("--non-constant-id");
        this.argsList.add("--output-text-symbols");
        this.argsList.add(workspace.binDir);
        this.argsList.add("-S");
        this.argsList.add(workspace.resDir);
        this.argsList.add("-J");
        this.argsList.add(workspace.genDir);
        this.argsList.add("-M");
        this.argsList.add(workspace.manifestXml);
        this.argsList.add("-I");
        this.argsList.add(workspace.androidJar);
        this.argsList.add("-F");
        this.argsList.add(workspace.outApkRes);
        if (commandRun(this.argsList) == 0) {
            return -1;
        }
        throw new SketchBuildException(this.argsList.toString() + "," + this.logcat.errMsgList.toString());
    }
}
