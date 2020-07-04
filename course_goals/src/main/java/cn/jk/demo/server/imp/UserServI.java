package cn.jk.demo.server.imp;


import cn.jk.demo.pojo.User;

import java.util.List;

public interface UserServI {
    List<User> showAll();
    List<User> showUserByKey(User user);
    User showOneUser(User user);
    Integer regist(User user);
    User Login(User user);
    Integer update(User user);
    Integer deleteList(List list);
    Integer deleteOne(Integer id);

}
