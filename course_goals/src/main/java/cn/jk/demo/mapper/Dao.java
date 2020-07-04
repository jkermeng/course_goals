package cn.jk.demo.mapper;

import java.util.List;

public interface Dao<T> {
    List<T> selectAll();

    T selectOneByKey(T t);

    T selectById(Integer id);

    Integer update(T t);

    Integer delete(T t);
    Integer deleteByList(List list);
    Integer insert(T t);

    boolean exist(T t);
}
