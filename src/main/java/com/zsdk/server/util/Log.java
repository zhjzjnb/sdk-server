package com.zsdk.server.util;

import org.apache.log4j.Logger;

/**
 * Created by zhj on 2017/3/14.
 */
public class Log {

    private static Logger logger = Logger.getLogger(Log.class.getName());


    public static void i(String msg) {
        logger.info(msg);
    }

    public static void i(String msg, Object... params) {
        msg = String.format(msg, params);
        logger.info(msg);
    }

    public static void d(String msg) {
        logger.debug(msg);
    }

    public static void d(String msg, Object... params) {
        msg = String.format(msg, params);
        logger.debug(msg);
    }

    public static void e(String msg) {
        logger.error(msg);
    }

    public static void e(String msg, Object... params) {
        msg = String.format(msg, params);
        logger.error(msg);
    }

    public static void e(String msg, Throwable throwable) {
        logger.error(msg, throwable);
    }
}
