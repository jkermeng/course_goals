package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper extends Dao<Role> {
    @Override
    List<Role> selectAll();

    @Override
    Role selectOneByKey(Role role);

    @Override
    Integer deleteByList(List list);

    @Override
    Role selectById(Integer id);

    @Override
    Integer update(Role role);

    @Override
    Integer delete(Role role);

    @Override
    Integer insert(Role role);

    @Override
    boolean exist(Role role);
}
