package SupLib.EXTools;

import SupLib.EXTools.BuildConfig;

import java.util.Map;

public class MapUtil {
    public static Object get(Map<String, Object> src, String key, Object defaultValue) {
        if (src == null) {
            return defaultValue;
        }
        Object obj = src.get(key);
        if (obj != null) {
            return obj;
        }
        return defaultValue;
    }

    public static int getInt(Map<String, Object> src, String key, int defaultValue) {
        Object obj = get(src, key, Integer.valueOf(defaultValue));
        if (obj instanceof Integer) {
            return ((Integer) get(src, key, Integer.valueOf(defaultValue))).intValue();
        }
        if (obj instanceof Double) {
            return ((Double) get(src, key, Integer.valueOf(defaultValue))).intValue();
        }
        return defaultValue;
    }

    public static int getInt(Map<String, Object> src, String key) {
        return getInt(src, key, -1);
    }

    public static long getLong(Map<String, Object> src, String key) {
        return ((Long) get(src, key, Integer.valueOf(-1))).longValue();
    }

    public static boolean getBoolean(Map<String, Object> src, String key) {
        return ((Boolean) get(src, key, Boolean.valueOf(false))).booleanValue();
    }

    public static boolean getBoolean(Map<String, Object> src, String key, boolean defaultValue) {
        return ((Boolean) get(src, key, Boolean.valueOf(defaultValue))).booleanValue();
    }

    public static String getString(Map<String, Object> src, String key) {
        return (String) get(src, key, BuildConfig.VERSION_NAME);
    }

    public static String getString(Map<String, Object> src, String key, String defaultValue) {
        return (String) get(src, key, defaultValue);
    }

    public static String[] getScFileName(Map<String, Object> src, String key) {
        return getString(src, key).split(";");
    }
}
