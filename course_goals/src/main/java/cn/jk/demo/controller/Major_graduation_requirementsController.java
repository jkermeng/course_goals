package cn.jk.demo.controller;

import cn.jk.demo.enums.Enums;
import cn.jk.demo.enums.Result;
import cn.jk.demo.pojo.*;
import cn.jk.demo.pojo.uentity.DefaultKnowledgeMajor;
import cn.jk.demo.pojo.uentity.Major_Graduation_IP_Part;
import cn.jk.demo.server.imp.Ass_MajorServI;
import cn.jk.demo.server.imp.Association_Graduation_RequirementsServI;
import cn.jk.demo.server.imp.Index_PointsServI;
import cn.jk.demo.server.imp.Major_graduation_requirementsServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("mgr")
public class Major_graduation_requirementsController {
    @Autowired
    private Major_graduation_requirementsServI major_graduation_requirementsServI;
    @Autowired
    private Ass_MajorServI ass_majorServI;
    @Autowired
    private Index_PointsServI index_pointsServI;

    @GetMapping("showAll")
    private Result showAll() {
        System.out.println("查询所有毕业要求...");
        List<Major_graduation_requirements> major_graduation_requirements = major_graduation_requirementsServI.showAll();//所有毕业要求
        List<Ass_Major> ass_majors = ass_majorServI.showAll();//框定的毕业要求表
        List<Index_Points> index_points = index_pointsServI.showAll();//所有毕业要求的指标

        for (int i = 0; i < major_graduation_requirements.size(); i++) {
            Major_graduation_requirements major_graduation_requirements1 = major_graduation_requirements.get(i);//一个毕业要求
            List<Association_Graduation_Requirements> asslist = new ArrayList<>();
            List<Major_Graduation_IP_Part> ipList = new ArrayList<>();

            for (Ass_Major am : ass_majors
            ) {
                if (am.getTable_major_graduation_requirements_tmgr_id().getTmgr_id() == major_graduation_requirements1.getTmgr_id()) {
                    asslist.add(am.getTable_association_graduation_requirements_tagr_id());
                }
            }//完成了协会要求的筛选
            major_graduation_requirements1.setMajor_index_point(asslist);
            int start = 1;
            List<Index_Points> index_points1 = new ArrayList<>();
            Major_Graduation_IP_Part major_graduation_ip_part = new Major_Graduation_IP_Part();
            int begin = 0;
            boolean flag = true;
            for (Index_Points ips : index_points
            ) {
                begin++;
                if (major_graduation_requirements1.getTmgr_id() == ips.getTable_major_graduation_requirements_tmgr_id().getTmgr_id() && start == ips.getTip_part()) {
                    if (flag) {
                        flag = false;
                        major_graduation_ip_part.setNum(start);
                        major_graduation_ip_part.setDescript(ips.getTip_describe());
                    }

                    index_points1.add(ips);
                    if (begin == index_points.size()) {
                        major_graduation_ip_part.setPointsList(index_points1);
                        ipList.add(major_graduation_ip_part);
                    }
                } else {
                    if (!flag) {
                        major_graduation_ip_part.setPointsList(index_points1);
                        ipList.add(major_graduation_ip_part);
                        index_points1 = new ArrayList<>();
                        major_graduation_ip_part = new Major_Graduation_IP_Part();
                        flag = true;
                        start = ips.getTip_part();
                        major_graduation_ip_part.setNum(start);
                        if (flag) {
                            flag = false;
                            major_graduation_ip_part.setNum(start);
                            major_graduation_ip_part.setDescript(ips.getTip_describe());
                        }
                        index_points1.add(ips);
                        if (begin == index_points.size()) {
                            major_graduation_ip_part.setPointsList(index_points1);
                            ipList.add(major_graduation_ip_part);
                        }
                    }
                }
            }
            major_graduation_requirements1.setPartsList(ipList);

        }
        return new Result(Enums.Success, major_graduation_requirements);
    }
}
