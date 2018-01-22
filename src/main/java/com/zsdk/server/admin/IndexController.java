package com.zsdk.server.admin;

import com.zsdk.server.bean.Result;
import com.zsdk.server.config.Configuration;
import com.zsdk.server.model.AdminUser;
import com.zsdk.server.service.AdminUserService;
import com.zsdk.server.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhj on 18/1/17.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
