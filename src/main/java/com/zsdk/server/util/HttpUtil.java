package com.zsdk.server.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsdk.server.client.LoginResult;
import com.zsdk.server.client.Result;
import com.zsdk.server.model.UserInfo;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhj on 2017/3/14.
 */
public class HttpUtil {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static Map<String, String> getHttpParams(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();


        Map<String, String> result = new HashMap<String, String>();
        Log.i("getHttpParams");
        for (Map.Entry<String, String[]> entry : map.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue()[0];
            Log.i("[%s]=[%s]", key, value);
            result.put(key, value);
        }
        return result;
    }

    public static String getHttpPostBodyString(HttpServletRequest
                                                       request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(request
                    .getInputStream(), "utf-8"));
            String ln;
            while ((ln = in.readLine()) != null) {
                stringBuilder.append(ln);
            }
        } catch (Exception e) {
            Log.e(e.getMessage(), e);
        }
        return stringBuilder.toString();
    }

    public static void replyToClient(HttpServletResponse response, Result<LoginResult> loginResult) {
        response.setHeader("Content-Type", "text/json;charset=UTF-8");

        String result = loginResult.toJson(LoginResult.class);
        try {
            response.getOutputStream().write(result.getBytes("UTF-8"));
            Log.i("replyToClient --> Client: " + result);
        } catch (IOException e) {
            Log.e(e.getMessage(), e);
        }
    }


    public static void optionSuccess(com.zsdk.server.bean.Result result) {
        result.setState(0);
        result.setMsg("success");
    }

    public static String get(String url) throws Exception {

        URL localURL = new URL(url);

        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        //响应失败
        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }

        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer.toString();
    }
}
