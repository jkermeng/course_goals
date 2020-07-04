package cn.jk.demo.server.imp;

import cn.jk.demo.pojo.Course;

import java.util.List;

public interface CourseServI {
    List<Course> selectAll();

    Course selecyById(Integer id);

    Integer addCourse(Course course);

    Integer updateMsg(Course course);

    Integer delCourse(Course course);
}
