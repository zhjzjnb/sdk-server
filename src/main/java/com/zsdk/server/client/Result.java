package com.zsdk.server.client;

import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * 封装json对象，所有返回结果都使用它
 */
public class Result<T> implements Serializable {

    private int code;// 是否成功标志

    private T data;// 成功时返回的数据

    private String msg;// 错误信息

    public Result() {
    }

    // 成功时的构造器
    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    // 错误时的构造器
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(Result.class, clazz);
        return gson.toJson(this, objectType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

}
