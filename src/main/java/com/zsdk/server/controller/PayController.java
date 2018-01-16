package com.zsdk.server.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhj on 17/3/17.
 */
@Controller
public class PayController {


    @RequestMapping("/alipay")
    @ResponseBody
    public void alipay(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }

}
