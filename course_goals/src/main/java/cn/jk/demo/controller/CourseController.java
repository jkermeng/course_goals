package cn.jk.demo.controller;

import cn.jk.demo.enums.Enums;
import cn.jk.demo.enums.Result;
import cn.jk.demo.pojo.Course;
import cn.jk.demo.pojo.User;
import cn.jk.demo.server.imp.CourseServI;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseServI courseServI;

    @GetMapping("showAll")
    private Result showAll() {
        System.out.println("查询到所有课程");
        List<Course> courses = courseServI.selectAll();
        return new Result(Enums.Success, courses);
    }

    @PostMapping("page")
    private Result showAllByPage(Integer page, Integer num) {
        if (page != null && num != null) {
            System.out.println("查询到所有课程" + "第：" + page + " 数量：" + num);
            PageHelper.startPage(page, num);
            List<Course> courses = courseServI.selectAll();
            System.out.println("已经分页1，10");
            PageInfo pageInfo = new PageInfo(courses);
            return new Result(Enums.Success, pageInfo);
        } else {
            return new Result(Enums.Null, "页数或显示数量为空值！");
        }
    }

    @PostMapping("showOne")
    private Result showOneCourse(Integer tc_id) {
        if (tc_id != null) {
            System.out.println("查询课程的id为：" + tc_id);
            Course course = courseServI.selecyById(tc_id);
            if (course != null) {
                return new Result(Enums.Success, course);
            } else {
                return new Result(Enums.Fail, "查询失败没有这个id");
            }
        } else {
            return new Result(Enums.Null, "课程id为空值！");

        }

    }

    @PostMapping("add")
    private Result addCourse(@RequestParam("u_id") Integer teach_ids, Course course) {
        if (course != null && teach_ids != null) {
            System.out.println("添加的课程属性" + course + "老师id" + teach_ids);
            User user = new User();
            user.setU_id(teach_ids);
            course.setTc_teacher(user);
            Integer integer = courseServI.addCourse(course);
            if (integer > 0) {
                return new Result(Enums.Success, "成功！");
            } else {
                return new Result(Enums.Fail, "失败！！");
            }
        } else {
            return new Result(Enums.Null, "课程或教师id空值！！");
        }
    }

    @PostMapping("update")
    private Result updateCourse(@RequestBody Course course) {
        if (course != null) {
            System.out.println("修改的课程属性" + course + "老师id" + course.getTc_teacher().getU_id());
            Integer integer = courseServI.updateMsg(course);
            if (integer > 0) {
                return new Result(Enums.Success, "成功！");
            } else {
                return new Result(Enums.Fail, "失败！！");
            }
        } else {
            return new Result(Enums.Null, "课程或教师id空值！！");
        }
    }

    @PostMapping("sameId")
    private Result isSameId(Course course) {
        if (course != null) {
            System.out.println("课程显示的tc_showid" + course.getTc_showid());
            List<Course> courses = courseServI.selectAll();
            boolean flag = false;
            for (Course c :
                    courses) {
                if (c.getTc_showid().equals(course.getTc_showid())) {
                    flag = true;
                }
            }
            return new Result(Enums.Success, flag);
        } else {
            return new Result(Enums.Null, "课程显示的tc_showid是空值Null！！");
        }
    }

    @PostMapping("del")
    private Result deleteCourse(Course course) {
        System.out.println("需要被删除的课程id>" + course.getTc_id());
        courseServI.delCourse(course);
        return new Result(Enums.Success, "需要被删除的课程！！"+course.getTc_id()+"成功删除");
    }

}
