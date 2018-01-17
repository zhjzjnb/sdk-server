package com.zsdk.server.admin;

import com.zsdk.server.util.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhj on 18/1/17.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/doLogin")
    private String doLogin(){
        Log.i("doLogin");
        return "admin/test";
    }
}
