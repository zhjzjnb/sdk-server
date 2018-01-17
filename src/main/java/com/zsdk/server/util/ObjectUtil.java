package com.zsdk.server.util;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhj on 17/3/16.
 */


public class ObjectUtil {


    public static boolean isPropertyNull(Object obj) {
        if (obj == null){
            return true;
        }
        boolean flag = false;
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(obj) == null) {
                    flag = true;
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            Log.e("isPropertyNull",e);
        }
        return flag;
    }
}



