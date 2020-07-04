package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Admin;
import cn.jk.demo.pojo.Ass_Major;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Repository
public interface Ass_MajorMapper extends Daos<Ass_Major>{
    @Override
    Set<Ass_Major> selectsByKey(Ass_Major ass_major);

    @Override
    HashSet<Ass_Major> selectAll();

     Set<Ass_Major> selectAlls();
    @Override
    Ass_Major selectById(Integer id);

}
