package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Course;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface CourseMapper extends Daos<Course>{
    @Override
    HashSet<Course> selectAll();

    @Override
    Course selectOneByKey(Course course);

    @Override
    HashSet<Course> selectsByKey(Course course);

    @Override
    Course selectById(Integer id);

    @Override
    Integer update(Course course);

    @Override
    Integer delete(Course course);

    @Override
    Integer deleteByList(List list);

    @Override
    Integer insert(Course course);
}
