package com.zsdk.server.util;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.Map;


/**
 * Created by zhj on 17/3/16.
 */
public class HttpAgent {

    public static String get(String url) throws IOException {
        return Request.Get(url).execute().returnContent().toString();
    }


    public static String postBodyForm(String url,Map<String,String> params) throws IOException {
        if (params == null){
            throw new RuntimeException("postBodyForm null params");
        }

        Form f = Form.form();
        for (Map.Entry<String,String> entry:params.entrySet()){
            f.add(entry.getKey(),entry.getValue());
        }

        return Request.Post(url).bodyForm(f.build()).execute().returnContent().toString();
    }

    public static String postBodyJson(String url,String body) throws IOException {
        if (body == null){
            throw new RuntimeException("postBodyString null body");
        }
        return Request.Post(url).bodyString(body,ContentType.APPLICATION_JSON).execute().returnContent().toString();
    }
}
