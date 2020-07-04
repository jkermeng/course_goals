package cn.jk.demo.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Daos<T> {
    Set<T> selectAll();

    T selectOneByKey(T t);

    Set<T> selectsByKey(T t);

    T selectById(Integer id);

    Integer update(T t);

    Integer delete(T t);

    Integer deleteByList(List list);

    Integer insert(T t);

}
