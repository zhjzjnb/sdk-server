package com.zsdk.server.interceptor;

import com.zsdk.server.util.Log;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhj on 17/3/16.
 */


public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        String reqURI = request.getRequestURI();
        Log.i("reqURI:"+reqURI);
//        if (reqURI.contains("doLogin")) {
//            return true;
//        } else {
//            HttpSession session = request.getSession();
//            Object obj = session.getAttribute("loginName");
//            if (null == obj || obj.toString().isEmpty()) {
//                response.sendRedirect("admin/login.jsp");
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}



