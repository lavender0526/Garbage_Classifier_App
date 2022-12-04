package com.example.sa.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonObjectUtil {
    public static String getNullableString(JSONObject jsonObject, String key) throws JSONException {
        if (jsonObject.isNull(key)) return null;
        return jsonObject.getString(key);
    }
}
