package cn.jk.demo.controller;

import cn.jk.demo.enums.Enums;
import cn.jk.demo.enums.Result;
import cn.jk.demo.pojo.Admin;
import cn.jk.demo.pojo.User;
import cn.jk.demo.server.imp.AdminServI;
import cn.jk.demo.server.imp.UserServI;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServI userServI;
    @Autowired
    private AdminServI adminServI;

    /**
     * 用户和管理员登入入口
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("doLogin")
    private Result Login(String username, String password) {
        if (username != null && !username.equals("") && password != null && !password.equals("")) {
            User user = new User();
            user.setU_name(username);
            user.setU_password(password);
            System.out.println(user);
            User login = userServI.Login(user);
            if (null != login && login.getU_id() != null && login.getU_id() != 0) {
                System.out.println(login);
                return new Result(Enums.Success, login);
            } else {
                Admin admin = new Admin();
                admin.setName(username);
                admin.setPassword(password);
                Admin login1 = adminServI.Login(admin);
                if (login1 != null) {
                    return new Result(Enums.Success, login1);
                } else {
                    return new Result(Enums.Fail, "登录失败");
                }
            }
        } else {
            return new Result(Enums.Null, "账号密码空值!!");
        }
    }


}
