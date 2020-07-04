package cn.jk.demo.controller;

import cn.jk.demo.enums.Enums;
import cn.jk.demo.enums.Result;
import cn.jk.demo.pojo.Major_graduation_requirements;
import cn.jk.demo.pojo.Role;
import cn.jk.demo.pojo.User;
import cn.jk.demo.server.imp.Major_graduation_requirementsServI;
import cn.jk.demo.server.imp.UserServI;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("admin")
@RestController
public class AdminsController {
    @Autowired
    private UserServI userServI;
    @Autowired
    private Major_graduation_requirementsServI major_graduation_requirementsServI;

    @GetMapping("showAll")
    private Result showAllUser(HttpSession session) {
        List<User> allUser = (List<User>) session.getAttribute("allUser");
        if (allUser != null) {
            System.out.println("有session值");

            return new Result(Enums.Success, allUser);
        } else {
            System.out.println("空");
            List<User> users = userServI.showAll();

            session.setAttribute("allUser", users);
            return new Result(Enums.Success, users);
        }
    }

    @PostMapping("page")
    private Result showAllUserByPage(HttpSession session, Integer page, Integer num) {
        if (page != null && num != null) {
            PageHelper.startPage(page, num);
            List<User> allUser = (List<User>) session.getAttribute("allUser");
            if (allUser != null) {
                System.out.println("分页有session值");
                PageInfo pageInfo = new PageInfo(allUser);
                return new Result(Enums.Success, pageInfo);
            } else {
                System.out.println("分页空");
                List<User> users = userServI.showAll();
                PageInfo pageInfo = new PageInfo(users);
                return new Result(Enums.Success, pageInfo);
            }
        } else {
            return new Result(Enums.Null, "页面或显示数量为Null值");
        }
    }

    @PostMapping("changePower")
    private Result changePower(User user, Integer role_Id) {
        System.out.println("正在修改的用户：" + user + "角色id" + role_Id);
        if (user != null && role_Id != null) {
            Role role = new Role();
            role.setRole_Id(role_Id);
            user.setRole_id(role);
            Integer update = userServI.update(user);
            System.out.println("已经修改的数量：" + update);
            if (update > 0) {
                return new Result(Enums.Success, "修改权限成功");
            } else {
                return new Result(Enums.Fail, "修改权限失败");
            }
        } else {
            return new Result(Enums.Null, "Null空值错误");
        }
    }

    @GetMapping("showTeacher")
    private Result showAllUpTeacher() {
        List<User> users = userServI.showAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRole_id().getRole_Id() == 3) {
                users.remove(users.get(i));
                i--;
            }
        }
        return new Result(Enums.Success, users);
    }

    @GetMapping("showStu")
    private Result showStu() {
        System.out.println("查询所有学生..");
        List<User> users = userServI.showAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRole_id().getRole_Id() != 3) {
                users.remove(users.get(i));
                i--;
            }
        }
        System.out.println("学生数量" + users.size());
        return new Result(Enums.Success, users);
    }

    @GetMapping("showMajor")
    private Result showMajor() {
        List<Major_graduation_requirements> mgr = major_graduation_requirementsServI.showAll();
        return new Result(Enums.Success, mgr);
    }

}
