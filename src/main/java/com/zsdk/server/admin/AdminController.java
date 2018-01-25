package com.zsdk.server.admin;

import com.zsdk.server.bean.Data;
import com.zsdk.server.bean.Result;
import com.zsdk.server.config.Configuration;
import com.zsdk.server.model.AdminInfo;
import com.zsdk.server.model.AdminUser;
import com.zsdk.server.service.AdminUserService;
import com.zsdk.server.util.HttpUtil;
import com.zsdk.server.util.Log;
import com.zsdk.server.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhj on 18/1/17.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(path = "/doLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private Result doLogin(HttpServletRequest request, @RequestBody AdminInfo adminInfo) {
        Result result = new Result();
        if (adminUserService.isValid(adminInfo)) {
            result.setState(Configuration.RESULT_CODE_SUCCESS);
            result.setMsg(Configuration.RESULT_MSG_SUCCESS);
            request.getSession().setAttribute("loginName", adminInfo.getUserName());

        } else {
            result.setState(Configuration.RESULT_CODE_FAIL);
            result.setMsg(Configuration.RESULT_MSG_FAIL);
        }
        return result;
    }

    @RequestMapping(path = "/addAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private Result addAdmin(@RequestBody AdminInfo adminInfo) {
        Result result = new Result();
        if (adminUserService.insert(adminInfo)) {
            result.setState(Configuration.RESULT_CODE_SUCCESS);
            result.setMsg(Configuration.RESULT_MSG_SUCCESS);
        } else {
            result.setState(Configuration.RESULT_CODE_FAIL);
            result.setMsg(Configuration.RESULT_MSG_FAIL);
        }
        return result;
    }

    @RequestMapping(path = "/removeAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result removeGame(@RequestParam int id) {
        Result result = new Result();
        if (id == 1) {
            return result;
        }
        HttpUtil.optionSuccess(result);
        adminUserService.delete(id);
        return result;
    }


    @RequestMapping(path = "/doExit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result exit(HttpServletRequest request) {
        request.getSession().removeAttribute("loginName");
        Result result = new Result();
        result.setState(Configuration.RESULT_CODE_SUCCESS);
        result.setMsg(Configuration.RESULT_MSG_SUCCESS);
        return result;
    }


    @RequestMapping(path = "/adminRoles", method = RequestMethod.GET)
    public String adminRoles() {
        return "adminRoles";
    }


    @RequestMapping(path = "/getAllAdmins", method = RequestMethod.POST)
    @ResponseBody
    public Data<AdminInfo> getAllAdmins() {
        Data<AdminInfo> data = new
                Data<AdminInfo>();
        List<AdminInfo> list = adminUserService.getAll();
        data.setTotal(list.size());
        data.setRows(list);
        return data;
    }


    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping(path = "/test")
    public void test() {
        Log.i("test:" + redisUtil.set("hello", "你好"));
        Log.i("test:" + redisUtil.get("hello"));


    }
}
