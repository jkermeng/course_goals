package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Association_Graduation_Requirements;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Association_Graduation_RequirementsMapper extends Daos<Association_Graduation_Requirements>{

    @Override
    Association_Graduation_Requirements selectById(Integer id);

    @Override
    Set<Association_Graduation_Requirements> selectAll();

    @Override
    Set<Association_Graduation_Requirements> selectsByKey(Association_Graduation_Requirements association_graduation_requirementsMapper);
}
