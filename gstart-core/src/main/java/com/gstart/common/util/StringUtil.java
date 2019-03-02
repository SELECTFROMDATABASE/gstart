package com.gstart.common.util;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class StringUtil extends StringUtils {
    public static String parseISO2UTF8(String iso) {
        try {
            String newString = new String(iso.getBytes(), "UTF-8");
            return newString;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";

    }

    public static String encodeURI2UTF8(String en) {
        if (en == null) {
            return null;
        }
        try {
            return URLEncoder.encode(en, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String getUrlWithQueryString(String url,Map<String, String> param) {
        if (param == null) {
            return url;
        }
        StringBuilder sd = new StringBuilder(url);

        if (url.contains("?") != true) {
            sd.append("?");
        }

        int t = 0;
        for (Map.Entry<String, String> e : param.entrySet()) {
            if (e.getValue() == null) {
                ++t;
                continue;
            }
            if (t == 0) {
                sd.append("");
                sd.append(e.getKey());
                sd.append("=");
                sd.append(encodeURI2UTF8(e.getValue()));
                ++t;
            } else {
                sd.append("&");
                sd.append(e.getKey());
                sd.append("=");
                sd.append(encodeURI2UTF8(e.getValue()));
            }
        }
        return sd.toString();

    }
}
