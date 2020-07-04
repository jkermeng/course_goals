package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Funs;

import java.util.List;

public interface FunsMapper extends Dao<Funs>{
    @Override
    List<Funs> selectAll();

    @Override
    Funs selectOneByKey(Funs funs);

    @Override
    Funs selectById(Integer id);

    List<Funs> selectByRoleId(Integer pid);

    List<Funs> selectByPid(Integer pid);
    @Override
    Integer update(Funs funs);

    @Override
    Integer delete(Funs funs);

    @Override
    Integer deleteByList(List list);

    @Override
    Integer insert(Funs funs);

    @Override
    boolean exist(Funs funs);
}
