package SupportClasses;

import java.util.Map;

public interface ApkMakingInterface {
    Map<String, String> generateAssetFiles();

    Map<String, String> generateReplaces();

    String getSourceAssetFile();

    String getTargetApkName();

    void onFinish(boolean z);
}
