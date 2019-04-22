package com.thbs.baseboilerplateandroid.persistence.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.thbs.baseboilerplateandroid.util.ObjectSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PreferenceUtils {

    private static List<String> result;

    private static SharedPreferences.Editor getPrefsEditor(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.edit();
    }

    //For handling String value
    public static void writePreferenceValue(Context context, String prefsKey, String prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(context);
        editor.putString(prefsKey, prefsValue);
        editor.commit();
    }


    // For handling Integer value
    public static void writePreferenceValue(Context context, String prefsKey, int prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(context);
        editor.putInt(prefsKey, prefsValue);
        editor.commit();
    }

    // For handling Boolean value
    public static void writePreferenceValue(Context context, String prefsKey, boolean prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(context);
        editor.putBoolean(prefsKey, prefsValue);
        editor.commit();
    }


    //For getting String value
    public static String getPreferenceStringValue(Context context, String prefsKey) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(prefsKey, "");
    }

    //For getting String value
    public static Integer getPreferenceIntValue(Context context, String prefsKey) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(prefsKey, 0);
    }

    //For getting String value
    public static Boolean getPreferenceBoolValue(Context context, String prefsKey) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(prefsKey, false);
    }

    // For handling Array value
    // For handling Array value
    public static void writePreferenceValue(Context context, String prefsKey, List<String> prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(context);
        try {
            editor.putString(prefsKey, ObjectSerializer.serialize(prefsValue));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }

    //For getting Array value
    public static List<String> getPreferenceArrayValue(Context context, String prefsKey) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            result = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString(prefsKey, ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //To clear SharedPreference
    public static void toClearValue(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().clear().apply();
    }

    public static void clearSp(Context context, String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().remove(key).apply();
    }


}
