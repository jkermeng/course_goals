package cn.jk.demo.server;

import cn.jk.demo.mapper.UserMapper;
import cn.jk.demo.pojo.Role;
import cn.jk.demo.pojo.User;
import cn.jk.demo.server.imp.UserServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServImp implements UserServI {
    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer regist(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        Role role = new Role();
        role.setRole_Id(2);
        user.setRole_id(role);
        System.out.println(user);
        User user1 = userMapper.selectOneByKey(user);
        if (user1 == null) {
            return userMapper.regist(user);
        } else {
            return 0;
        }
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User Login(User user) {
        return userMapper.selectOneByKey(user);
    }

    /**
     * 显示所有用户
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<User> showAll() {
        return userMapper.selectAll();
    }

    /**
     * 根据关键词查找用户
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<User> showUserByKey(User user) {
        return userMapper.selectByKey(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User showOneUser(User user) {
        return userMapper.selectOneByKey(user);
    }

    /**
     * 通过id删除用户
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deleteOne(Integer id) {
        List arrayList = new ArrayList();
        arrayList.add(id);
        return userMapper.deleteByList(arrayList);
    }

    /**
     * 批量删除用户
     *
     * @param list
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deleteList(List list) {
        return userMapper.deleteByList(list);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer update(User user) {
        return userMapper.update(user);
    }


}
