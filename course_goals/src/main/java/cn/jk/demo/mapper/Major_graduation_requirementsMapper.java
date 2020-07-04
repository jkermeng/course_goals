package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Major_graduation_requirements;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Major_graduation_requirementsMapper extends Daos<Major_graduation_requirements>{
    @Override
    Major_graduation_requirements selectById(Integer id);

    @Override
    Set<Major_graduation_requirements> selectAll();

    @Override
    Set<Major_graduation_requirements> selectsByKey(Major_graduation_requirements major_graduation_requirements);
}
