package cn.jk.demo.mapper;

import cn.jk.demo.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends Dao<User> {
    @Override
    List<User> selectAll();

    @Override
    User selectOneByKey(User user);

    List<User> selectByKey(User user);

    @Override
    User selectById(Integer id);

    List<User> selectsById(Integer id);

    @Override
    Integer update(User user);

    @Override
    Integer delete(User user);

    @Override
    Integer deleteByList(List list);

    @Override
    Integer insert(User user);

    Integer regist(User user);

    @Override
    boolean exist(User user);
}
