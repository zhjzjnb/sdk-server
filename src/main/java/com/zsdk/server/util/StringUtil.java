package com.zsdk.server.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import com.zsdk.server.config.Configuration;
import org.apache.log4j.Logger;

/**
 * Created by zhj on 17/12/11.
 */
public class StringUtil {
    private static final Logger logger = Logger.getLogger(StringUtil.class);




    public static String getSignData(Map<String, String> params, List<String> keys,  String connector,String separator) {
        StringBuffer content = new StringBuffer();

        //按照自然升序处理
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (value != null && value.length() > 0) {
                content.append((i == 0 ? "" : connector) + key + separator +
                        value);
            }
        }
        return content.toString();
    }

    public static boolean checkInput(String str){
        if(str == null){
            return false;
        }
        if(str.length() < Configuration.INPUT_MIN_LEN
                || str.length() > Configuration.INPUT_MAX_LEN) {
            return false;
        }
        return str.matches("[\\w]+");

    }





    /**
     * 将map中参数生成签名字符串
     *
     * @param params
     * @return
     */
    public static String getSignData(Map<String, String> params) {
        return getSignData(params, "&");
    }

    public static String getSignData(Map<String, String> params, String
            connector) {
        return getSignData(params, connector, true);
    }

    public static String getSignDataWithoutKeySorted(Map<String, String>
                                                             params, String connector) {
        return getSignData(params, connector, false);
    }

    public static String getSignData(Map<String, String> params, String
            connector, String separator) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        //按照自然升序处理
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (value != null) {
                content.append((i == 0 ? "" : connector) + key + separator +
                        value);
            } else {
                content.append((i == 0 ? "" : connector) + key + separator);
            }
        }
        return content.toString();
    }

    public static String getSignDataWithoutKeys(Map<String, String> params,
                                                String connector) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        //按照自然升序处理
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (value != null) {
                content.append((i == 0 ? "" : connector) + value);
            } else {
                content.append((i == 0 ? "" : connector));
            }
        }
        return content.toString();
    }

    public static String getSignData(Map<String, String> params, String
            connector, boolean isKeySorted) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());

        if (isKeySorted) {
            //按照自然升序处理
            Collections.sort(keys);
        }

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (value != null) {
                content.append((i == 0 ? "" : connector) + key + "=" + value);
            } else {
                content.append((i == 0 ? "" : connector) + key + "=");
            }
        }
        return content.toString();

    }

    public static String getSignDataSortByKeyWithValueUrlEncode(Map<String, String>
                                                                        params) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());

        //按照自然升序处理
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            try {
                value = URLEncoder.encode(value, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (value != null) {
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            } else {
                content.append((i == 0 ? "" : "&") + key + "=");
            }
        }
        return content.toString();

    }


    /**
     * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static Map<String, String> splitQueryString(String queryString)
            throws UnsupportedEncodingException {
        Map<String, String> queryPairs = new LinkedHashMap<String, String>();
        String[] params = queryString.split("&");
        for (String param : params) {
            String[] pairs = param.split("=");
            String key = URLDecoder.decode(pairs[0], "UTF-8");
            String value = URLDecoder.decode(pairs[1], "UTF-8");
            queryPairs.put(key, value);
        }
        return queryPairs;
    }

    public static float getPriceFromProductId(String productId) {
        logger.debug("product_id:" + productId);
        String[] tokens = productId.split("\\.");
        logger.debug("RMB price:" + tokens[tokens.length - 1]);
        return Float.valueOf(tokens[tokens.length - 1]);
    }

    public static String generateRandomString(int length) {
        char[] elements = {'3', '4', '5', '7', '8', 'a', 'c', 'd', 'e', 'f',
                'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y'};
        StringBuffer stringBuffer = new StringBuffer();

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < length; i++) {
            stringBuffer.append(elements[random.nextInt(elements.length)]);
        }
        return stringBuffer.toString();
    }

    public static String getYearAndMonth() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        return format.format(date);
    }
}
