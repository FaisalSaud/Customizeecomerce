package SupLib.EXTools;

import android.os.Environment;

//import com.besome.sketch.lib.widgets.WidgetButton;
//import com.besome.sketch.lib.widgets.WidgetCheckBox;
//import com.besome.sketch.lib.widgets.WidgetEditText;
//import com.besome.sketch.lib.widgets.WidgetImageView;
//import com.besome.sketch.lib.widgets.WidgetLinearLayout;
//import com.besome.sketch.lib.widgets.WidgetListView;
//import com.besome.sketch.lib.widgets.WidgetSpinner;
//import com.besome.sketch.lib.widgets.WidgetTextView;
//import com.facebook.share.internal.ShareConstants;

//import org.bouncycastle.asn1.cmp.PKIBody;
//import org.bouncycastle.asn1.misc.NetscapeCertType;
//import org.bouncycastle.asn1.ocsp.OCSPResponseStatus;

import java.io.File;

public class Defines {
    public static final String CHARGE = "CHARGE";
    public static final int CHOICE_TYPE_EDITOR = 1;
    public static final int CHOICE_TYPE_NORMAL = 0;
    public static final int CHOICE_TYPE_SKETCHWARE = 2;
    public static final long DAY_TIME = 86400000;
    public static final boolean DEBUG = false;
    //public static final int[] EVTS = new int[]{EVT_ON_SESSION_UPDATE, EVT_ON_MYSC_UPDATE, EVT_ON_SHARED_UPDATE};
    public static final int EVT_ON_MYSC_UPDATE = 7001;
    public static final int EVT_ON_SESSION_UPDATE = 7000;
    public static final int EVT_ON_SHARED_UPDATE = 7002;
    public static final String FCM_TYPE_COMMENT = "COMMENT";
    public static final String FCM_TYPE_LIKE = "LIKE";
    public static final String FCM_TYPE_OTP = "OTP";
    public static final String FILE_JAVA_APPLICATION = "SketchApplication.java";
    public static final String FILE_JAVA_DEBUG_ACTIVITY = "DebugActivity.java";
    public static final String FILE_JAVA_MAIN = "MainActivity.java";
    public static final String FILE_JAVA_MEMO = "MemoActivity.java";
    public static final String FILE_XML_MAIN = "main.xml";
    public static final String FILE_XML_MANIFEST = "AndroidManifest.xml";
    public static final String FILE_XML_MEMO = "memo.xml";
    public static final String FLAG_N = "N";
    public static final String FLAG_Y = "Y";
    public static final String FREE = "FREE";
    public static final String GOOGLE_COMMUNITY = "/communities/102638901046739612618";
    public static final String HOST_URL = "http://www.sketchware.io";
    public static final long MONTH3_TIME = 8035200000L;
    public static final long MONTH6_TIME = 16070400000L;
    public static final long MONTH_TIME = 2678400000L;
    public static final String PERF_ITEM_SOURCE_SIZE = "P3I0";
    public static final String PREF_ITEM_ASK_LOGIC = "P1I5";
    public static final String PREF_ITEM_CHK_KEEP_LOGIN = "P2I0";
    public static final String PREF_ITEM_FCM_COUNT = "P16I0";
    public static final String PREF_ITEM_FCM_ID = "P16I1";
    public static final String PREF_ITEM_INAPP_ITEM_NAME = "P4I3";
    public static final String PREF_ITEM_INAPP_ORDER_NO = "P4I0";
    public static final String PREF_ITEM_INAPP_PRICE = "P4I1";
    public static final String PREF_ITEM_INAPP_PRODUCT_ID = "P4I2";
    public static final String PREF_ITEM_INAPP_PURCHASE_TIME = "P4I4";
    public static final String PREF_ITEM_INSTALL_AAPT = "P1I1";
    public static final String PREF_ITEM_INSTALL_ANDROID_JAR = "P1I2";
    public static final String PREF_ITEM_INSTALL_TUTORIAL = "P1I6";
    public static final String PREF_ITEM_INSTALL_VERSION = "P1I0";
    public static final String PREF_ITEM_LOGIN_ID = "P2I2";
    public static final String PREF_ITEM_LOGIN_PWD = "P2I3";
    public static final String PREF_ITEM_MAIN_TAB_IDX = "P0I1";
    public static final String PREF_ITEM_PERMISSION_CONTACT = "P23I2";
    public static final String PREF_ITEM_PERMISSION_STORAGE = "P23I1";
    public static final String PREF_ITEM_SC_HELP_SPN_IDX = "P0I0";
    public static final String PREF_ITEM_SNS_KIND = "P2I5";
    public static final String PREF_ITEM_SNS_LOGIN = "P2I4";
    public static final String PREF_ITEM_SYS_LANG_SET = "P12I3";
    public static final String PREF_ITEM_SYS_MYSC_AUTO_SAVE = "P12I2";
    public static final String PREF_ITEM_SYS_PUSH_ENABLE = "P12I4";
    public static final String PREF_ITEM_SYS_PUSH_SOUND = "P12I5";
    public static final String PREF_ITEM_SYS_SC_SORT = "P12I1";
    public static final String PREF_ITEM_SYS_SET_VIBRATION = "P12I0";
    public static final String PREF_ITEM_SYS_USE_SLACK_APP = "P12I6";
    public static final String PREF_ITEM_USER_ALIAS = "P2I6";
    public static final String PREF_ITEM_USER_ID = "P2I1";
    public static final String PREF_ITEM_USER_LEVEL = "P2I7";
    public static final String PREF_SEPARATOR = "_";
    public static final int READ_TIMEOUT = 15000;
    public static final int REQ_CD_ALL_INIT = 105;
    public static final int REQ_CD_EDIT_PROPERTY = 213;
    public static final int REQ_CD_FINDPWD = 103;
    public static final int REQ_CD_GOOGLE_LOGIN = 101;
    public static final int REQ_CD_IMPORT_ICON = 210;
    public static final int REQ_CD_IMPORT_SOUND = 219;
    public static final int REQ_CD_IN_APP = 202;
    public static final int REQ_CD_IN_APP_FOR_CUSTOM_INIT = 205;
    public static final int REQ_CD_JOIN = 102;
    public static final int REQ_CD_LOGIN = 100;
    public static final int REQ_CD_LOGOUT = 104;
    public static final int REQ_CD_MNG_IMAGE = 209;
    public static final int REQ_CD_MNG_IMAGE_PICK = 215;
    public static final int REQ_CD_MNG_SCREEN = 208;
    public static final int REQ_CD_MNG_SOUND = 217;
    public static final int REQ_CD_MNG_SOUND_PICK = 218;
    public static final int REQ_CD_NOTICE_READ = 106;
    public static final int REQ_CD_PERMISSION_CONTACT = 9500;
    public static final int REQ_CD_PERMISSION_STORAGE = 9501;
    public static final int REQ_CD_PURCHASE = 505;
    public static final int REQ_CD_SC_DESIGN = 204;
    public static final int REQ_CD_SC_INIT_SET = 200;
    public static final int REQ_CD_SC_INIT_SET_CUSTOM = 206;
    public static final int REQ_CD_SC_PICK_ICON = 207;
    public static final int REQ_CD_SC_PICK_ICON_WITHCROP = 216;
    public static final int REQ_CD_SC_START = 201;
    public static final int REQ_CD_SERVICEOUT = 199;
    public static final int REQ_CD_SHARE_PROJECT = 211;
    public static final int REQ_CD_SHARE_PROJECT_UPDATE = 212;
    public static final int REQ_CD_SHOW_ALL_PROJECT = 214;
    public static final int REQ_CD_SYS_GALLERY = 900;
    public static final int REQ_CD_SYS_SETTING = 107;
    public static final int REQ_CD_TUTORIAL = 110;
    public static final int REQ_CD_USER_PROFILE = 108;
    public static final int REQ_CD_VIDEO_TUTO = 180;
    public static final String SHARED_PREF_CUR_POSITION = "P0";
    public static final String SHARED_PREF_DATA_CURRENT_FILES = "P21";
    public static final String SHARED_PREF_DATA_CURRENT_LOGIC = "P20";
    public static final String SHARED_PREF_DATA_CURRENT_RESOURCES = "P22";
    public static final String SHARED_PREF_DATA_CURRENT_VIEW = "P19";
    public static final String SHARED_PREF_DATA_FILES = "P17";
    public static final String SHARED_PREF_DATA_LOGIC = "P14";
    public static final String SHARED_PREF_DATA_RESOURCES = "P18";
    public static final String SHARED_PREF_DATA_TEMP_FILES = "P32";
    public static final String SHARED_PREF_DATA_TEMP_LOGIC = "P31";
    public static final String SHARED_PREF_DATA_TEMP_RESOURCES = "P33";
    public static final String SHARED_PREF_DATA_TEMP_VIEW = "P30";
    public static final String SHARED_PREF_DATA_VIEW = "P13";
    public static final String SHARED_PREF_FCM = "P16";
    public static final String SHARED_PREF_INAPP = "P4";
    public static final String SHARED_PREF_INSTALL = "P1";
    public static final String SHARED_PREF_LOGIN = "P2";
    public static final String SHARED_PREF_MYSC = "P15";
    public static final String SHARED_PREF_PERMISSION = "P23";
    public static final String SHARED_PREF_SOURCE = "P3";
    public static final String SHARED_PREF_SYS_SET = "P12";
    public static final String SHARED_PREF_WIDGET_ALIGN = "P9";
    public static final String SHARED_PREF_WIDGET_IMAGE = "P11";
    public static final String SHARED_PREF_WIDGET_IME = "P8";
    public static final String SHARED_PREF_WIDGET_LAYOUT = "P5";
    public static final String SHARED_PREF_WIDGET_LISTVIEW = "P11.1";
    public static final String SHARED_PREF_WIDGET_MARGIN = "P6";
    public static final String SHARED_PREF_WIDGET_PADDING = "P7";
    public static final String SHARED_PREF_WIDGET_SPINNER = "P11.2";
    public static final String SHARED_PREF_WIDGET_TEXT = "P10";
    public static final String SKETCH = ".sketchware";
    public static final String SKETCH_DOWNLOAD = (SKETCH + File.separator + "download");
    public static final String SKETCH_DOWNLOAD_APK = (SKETCH_DOWNLOAD + File.separator + "apk");
   // public static final String SKETCH_DOWNLOAD_DATA = (SKETCH_DOWNLOAD + File.separator + ShareConstants.WEB_DIALOG_PARAM_DATA);
    public static final String SKETCH_MYSC = (SKETCH + File.separator + "mysc");
    public static final String SKETCH_RES_HOME = (SKETCH + File.separator + "resources");
    public static final String SKETCH_RES_ICON = (SKETCH + File.separator + "resources" + File.separator + "icons");
    public static final String SKETCH_RES_IMAGE = (SKETCH + File.separator + "resources" + File.separator + "images");
    public static final String SKETCH_RES_SOUND = (SKETCH + File.separator + "resources" + File.separator + "sounds");
    //public static final String SKETCH_TEMP_DATA = (SKETCH + File.separator + "temp" + File.separator + ShareConstants.WEB_DIALOG_PARAM_DATA);
    public static final String SKETCH_TEMP_ICONPACK = (SKETCH + File.separator + "temp" + File.separator + "iconpack");
    public static final String SKETCH_TEMP_IMG = (SKETCH + File.separator + "temp" + File.separator + "images");
    public static final String SKETCH_TEMP_PROJ = (SKETCH + File.separator + "temp" + File.separator + "proj");
    public static final String SKETCH_TEMP_SOUND = (SKETCH + File.separator + "temp" + File.separator + "sounds");
    public static final String SNS_KIND_FACEBOOK = "facebook";
    public static final String SNS_KIND_GOOGLE = "google";
    public static final int SOCKET_TIMEOUT = 15000;
    public static final int SUBS_DEFAULT = 0;
    //public static final long[] SUBS_KEEP_TIMES = new long[]{MONTH_TIME, MONTH3_TIME, MONTH6_TIME, YEAR_TIME, DAY_TIME, 642816000000L};
    public static final String[] SUBS_KINDS = new String[]{"subs_month_01", "subs_month_03", "subs_month_06", "subs_year_01", FREE, "unrestricted_voucher"};
    public static final int USER_LEVEL_ADMIN = 9;
    public static final long WEEK_TIME = 604800000;
    public static final int WIDGET_TYPE_MARGIN = 0;
    public static final int WIDGET_TYPE_PADDING = 1;
    public static final long YEAR_TIME = 32140800000L;
    static File sdcard = Environment.getExternalStorageDirectory();
    //public static final String[] widgets = new String[]{WidgetButton.class.getSimpleName(), WidgetCheckBox.class.getSimpleName(), WidgetEditText.class.getSimpleName(), WidgetImageView.class.getSimpleName(), WidgetLinearLayout.class.getSimpleName(), WidgetTextView.class.getSimpleName(), WidgetSpinner.class.getSimpleName(), WidgetListView.class.getSimpleName()};

    public static String getInAppKey() {
        return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxqe7Fu3i3VfnKRSRRljTsMuk7Br0dXaFdGnMNCzMGLQ72PSTEAUo4sXs5+Utmdf9R2s2tZyArdnehk9+Q72F0XzEZGeVfgzfLky7ffuk04yxUye/FlXBun/s0F7g2496+PyfXCP9jIBdncvQ9kaT8Xn6F/j0s2TqS/6xlCD38eYgCVFyp1mld1vYhGCZBlQvXFVJAoKCzqN2QZVO5KarkyTQSGeudvV/UQsJgyHh5zTZKnla1VIVj1Wl3nBb//s2dsmFnAx3500Y/h//XHveLUS7BkP34AGGWPLuoyJruLNvrZ3uUNDnCgnW4+z8Ilaj2SwCTeqQvvw/suZdExs88QIDAQAB";
    }

    public static String getSketchHomeHome() {
        return new File(sdcard, SKETCH + File.separator).getAbsolutePath();
    }

    public static String getSketchMyscHome() {
        return new File(sdcard, SKETCH_MYSC).getAbsolutePath();
    }

    public static String getMyScWorkSpaceHome(String scId) {
        return new File(sdcard, SKETCH_MYSC + File.separator + scId).getAbsolutePath();
    }

    public static String getTempWorkSpaceHome() {
        return new File(sdcard, SKETCH_TEMP_PROJ).getAbsolutePath();
    }

//    public static String getTempDataHome() {
//        return new File(sdcard, SKETCH_TEMP_DATA).getAbsolutePath();
//    }

    public static String getTempImgHome() {
        return new File(sdcard, SKETCH_TEMP_IMG).getAbsolutePath();
    }

    public static String getTempSoundHome() {
        return new File(sdcard, SKETCH_TEMP_SOUND).getAbsolutePath();
    }

    public static String getIconPackHome() {
        return new File(sdcard, SKETCH_TEMP_ICONPACK).getAbsolutePath();
    }

    public static String getResHome() {
        return new File(sdcard, SKETCH_RES_HOME).getAbsolutePath();
    }

    public static String getIconHome() {
        return new File(sdcard, SKETCH_RES_ICON).getAbsolutePath();
    }

    public static String getImageHome() {
        return new File(sdcard, SKETCH_RES_IMAGE).getAbsolutePath();
    }

    public static String getSoundHome() {
        return new File(sdcard, SKETCH_RES_SOUND).getAbsolutePath();
    }

    public static String getDownloadHome() {
        return new File(sdcard, SKETCH_DOWNLOAD).getAbsolutePath();
    }

    public static String getDownloadApkHome() {
        return new File(sdcard, SKETCH_DOWNLOAD_APK).getAbsolutePath();
    }

//    public static String getDownloadDataHome() {
//        return new File(sdcard, SKETCH_DOWNLOAD_DATA).getAbsolutePath();
//    }

    public static String getResZipPath(String scId) {
        return "resource" + File.separator + scId + File.separator + "res.zip";
    }

    public static String getSketchAppCompatHome() {
        return new File(sdcard, SKETCH + File.separator + "appcompat_v7").getAbsolutePath();
    }

//    public static String getWidgetPrefix(int viewType) {
//        String prefix = "widget";
//        switch (viewType) {
//            case WIDGET_TYPE_MARGIN /*0*/:
//                return "linear";
//            case CHOICE_TYPE_SKETCHWARE /*2*/:
//                return "hscroll";
//            case OCSPResponseStatus.TRY_LATER /*3*/:
//                return "button";
//            case NetscapeCertType.sslCA /*4*/:
//                return "textview";
//            case OCSPResponseStatus.SIG_REQUIRED /*5*/:
//                return "edittext";
//            case OCSPResponseStatus.UNAUTHORIZED /*6*/:
//                return "imageview";
//            case PKIBody.TYPE_KEY_UPDATE_REQ /*7*/:
//                return "webview";
//            case USER_LEVEL_ADMIN /*9*/:
//                return "listview";
//            case PKIBody.TYPE_KEY_RECOVERY_REP /*10*/:
//                return "spinner";
//            case PKIBody.TYPE_REVOCATION_REQ /*11*/:
//                return "checkbox";
//            case PKIBody.TYPE_REVOCATION_REP /*12*/:
//                return "vscroll";
//            default:
//                return prefix;
//        }
//    }
}
