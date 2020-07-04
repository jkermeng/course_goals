package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper extends Dao<Admin> {
    @Override
    List<Admin> selectAll();

    @Override
    Integer deleteByList(List list);

    @Override
    Admin selectOneByKey(Admin admin);

    @Override
    Admin selectById(Integer id);

    @Override
    Integer update(Admin admin);

    @Override
    Integer delete(Admin admin);

    @Override
    Integer insert(Admin admin);

    @Override
    boolean exist(Admin admin);
}
