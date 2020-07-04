package cn.jk.demo.controller;

import cn.jk.demo.enums.Enums;
import cn.jk.demo.enums.Result;
import cn.jk.demo.mapper.Test_KP_IPMapper;
import cn.jk.demo.pojo.*;
import cn.jk.demo.pojo.uentity.Chapter;
import cn.jk.demo.pojo.uentity.DefaultKnowledgeMajor;
import cn.jk.demo.pojo.uentity.KP_Index_Data;
import cn.jk.demo.server.imp.Ass_MajorServI;
import cn.jk.demo.server.imp.KP_index_PointsServI;
import cn.jk.demo.server.imp.Knowledge_PointsServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("kpc")
public class Knowledge_PointsController {
    @Autowired
    private Knowledge_PointsServI knowledge_pointsServI;
    @Autowired
    private KP_index_PointsServI kp_index_pointsServI;
    @Autowired
    private Ass_MajorServI ass_majorServI;

    @GetMapping("model")
    private Result getModel() {
        Knowledge_Points knowledge_points = new Knowledge_Points();
        List<Ass_Major> ass_majors = ass_majorServI.showAll();
        List<DefaultKnowledgeMajor> defaultKnowledgeMajors = new ArrayList<>();
        DefaultKnowledgeMajor defaultKnowledgeMajor = new DefaultKnowledgeMajor();
        int star = 1;
        boolean flag = true;
        for (int i = 0; i < ass_majors.size(); i++) {
            List<Association_Graduation_Requirements> association_graduation_requirements = new ArrayList<Association_Graduation_Requirements>();
            Ass_Major ass_major = ass_majors.get(i);
            if (ass_major.getTable_major_graduation_requirements_tmgr_id().getTmgr_id() == star) {
                //毕业要求相同
                defaultKnowledgeMajor.setMajor_graduation_requirements(ass_major.getTable_major_graduation_requirements_tmgr_id());
                if (flag) {
                    for (Ass_Major am : ass_majors
                    ) {
                        if (am.getTable_major_graduation_requirements_tmgr_id().getTmgr_id() == star) {
                            am.getTable_association_graduation_requirements_tagr_id().setCheckBox(false);
                            association_graduation_requirements.add(am.getTable_association_graduation_requirements_tagr_id());
                        }
                    }
                    defaultKnowledgeMajor.setAssociation_Graduation_Requirements(association_graduation_requirements);
                    flag = false;
                }
                if (i + 1 == ass_majors.size()) {
                    defaultKnowledgeMajors.add(defaultKnowledgeMajor);
                }
            } else {
                //毕业要求不同
                defaultKnowledgeMajors.add(defaultKnowledgeMajor);
                star = ass_major.getTable_major_graduation_requirements_tmgr_id().getTmgr_id();
                defaultKnowledgeMajor = new DefaultKnowledgeMajor();
                association_graduation_requirements = new ArrayList<Association_Graduation_Requirements>();
                flag = true;
                //毕业要求相同
                defaultKnowledgeMajor.setMajor_graduation_requirements(ass_major.getTable_major_graduation_requirements_tmgr_id());
                if (flag) {
                    for (Ass_Major am : ass_majors
                    ) {
                        if (am.getTable_major_graduation_requirements_tmgr_id().getTmgr_id() == star) {
                            am.getTable_association_graduation_requirements_tagr_id().setCheckBox(false);
                            association_graduation_requirements.add(am.getTable_association_graduation_requirements_tagr_id());
                        }
                    }
                    defaultKnowledgeMajor.setAssociation_Graduation_Requirements(association_graduation_requirements);
                    flag = false;
                }
                if (i + 1 == ass_majors.size()) {
                    defaultKnowledgeMajors.add(defaultKnowledgeMajor);
                }
            }
        }
        knowledge_points.setDefaultKnowledgeMajors(defaultKnowledgeMajors);
        return new Result(Enums.Success, knowledge_points);
    }

    @GetMapping("cid")
    private Result showByCourseId(Course course) {
        //课程搜索课程下的知识点
        if (course != null) {
            System.out.println("通过课程id查询到的知识点");
            Knowledge_Points knowledge_point = new Knowledge_Points();
            knowledge_point.setTable_course_tc_id(course);
            List<Chapter> map = knowledge_pointsServI.showByCourseId(knowledge_point);
            return new Result(Enums.Success, map);
        } else {
            return new Result(Enums.Null, "课程id空值");
        }
    }

    @PostMapping("add")
    private Result addKnowPoint(@RequestBody Knowledge_Points knowledge_points) {
        if (knowledge_points != null) {
            System.out.println("添加进来的知识点" + knowledge_points + "关键参数课程id+" + knowledge_points.getTable_course_tc_id().getTc_id());
            System.out.println("tkp_part代表同一章节下的知识点" + knowledge_points.getTkp_part());
            System.out.println("以上参数没问题则通过第一道添加方法");
            Integer integer = knowledge_pointsServI.addKnowledge_Points(knowledge_points);
            Knowledge_Points knowledge_points1 = knowledge_pointsServI.showByKnowledge_PointsId(knowledge_points);
            System.out.println(".......已经添加了知识点......");
            System.out.println("开始添加知识点下被勾选的checbox...");
            if (integer > 0 && knowledge_points.getDefaultKnowledgeMajors().size() > 0) {
                List<DefaultKnowledgeMajor> defaultKnowledgeMajors = knowledge_points.getDefaultKnowledgeMajors();
                for (DefaultKnowledgeMajor dkm : defaultKnowledgeMajors
                ) {
                    System.out.println("第" + dkm.getMajor_graduation_requirements().getTmgr_id() + "个" + dkm.getMajor_graduation_requirements().getTmgr_tile());
                    for (Association_Graduation_Requirements agr : dkm.getAssociation_Graduation_Requirements()
                    ) {
                        System.out.println("id" + agr.getTagr_id() + "协会毕业要求名称>" + agr.getTagr_title() + "查看被勾选的checbox" + agr.getCheckBox());
                        if (agr.getCheckBox()) {
                            KP_index_Points kp_index_points = new KP_index_Points();
                            kp_index_points.setTable_major_g(dkm.getMajor_graduation_requirements());
                            kp_index_points.setTable_knowledge_points_tkp_id(knowledge_points1);
                            kp_index_points.setTable_part(agr.getTagr_id());
                            kp_index_pointsServI.addKP_index_Points(kp_index_points);
                        } else {
                            System.out.println("没有指标");
                        }
                    }
                }
            }
            return new Result(Enums.Success, "添加知识点成功！");
        } else {
            return new Result(Enums.Null, "Null错误！");
        }
    }

    @Autowired
    private Test_KP_IPMapper test_kp_ipMapper;

    @PostMapping("update")
    private Result updateKnowPoint(@RequestBody List<Chapter> map) {
        if (map != null && map.size() > 0) {
            for (Chapter ct : map
            ) { //默认获取课程id
                System.out.println("获取关键数据第>" + ct.getNum() + "章");
                for (Knowledge_Points kp : ct.getPointsList()
                ) {
                    System.out.println("课程id>" + kp.getTable_course_tc_id().getTc_id());
                    System.out.println("知识点：" + kp.getTkp_id() + kp.getTkp_part() + kp.getTkp_describe() + kp.getTkp_title());
                    for (DefaultKnowledgeMajor dkm : kp.getDefaultKnowledgeMajors()
                    ) {
                        System.out.println("毕业要求id>" + dkm.getMajor_graduation_requirements().getTmgr_id() + "毕业要求名：" + dkm.getMajor_graduation_requirements().getTmgr_tile());
                        List<Association_Graduation_Requirements> association_graduation_requirements = dkm.getAssociation_Graduation_Requirements();
                        for (Association_Graduation_Requirements agr : association_graduation_requirements
                        ) {
                            KP_index_Points kp_index_points = new KP_index_Points();
                            kp_index_points.setTable_knowledge_points_tkp_id(kp);
                            kp_index_points.setTable_major_g(dkm.getMajor_graduation_requirements());
                            kp_index_points.setTable_part(agr.getTagr_id());
                            System.out.println("修费毕业要求id" + agr.getTagr_id() + "协会毕业要求 需要修改的值>" + agr.getCheckBox());
                            System.out.println(kp.getTkp_id() + ":::" + dkm.getMajor_graduation_requirements().getTmgr_id() + ":::" + agr.getTagr_id());
                            List<KP_index_Points> kp_index_points1 = kp_index_pointsServI.showBytkp_id(kp_index_points);
                            if (null != agr.getCheckBox() && agr.getCheckBox()) {
                                System.out.println("\n\n修改达成\n\n");
                                System.out.println("判断是否存在" + kp_index_points);
                                if (kp_index_points1.size() == 0) {
                                    kp_index_pointsServI.addKP_index_Points(kp_index_points);
                                }
                            } else {
                                for (KP_index_Points kip : kp_index_points1
                                ) {
                                    Test_KP_IP test_kp_ip = new Test_KP_IP();
                                    test_kp_ip.setTtkp_ip(kip.getTip_kp_id());
                                    test_kp_ipMapper.delete(test_kp_ip);
                                    System.out.println("删除" + kp_index_points);
                                }
                                kp_index_pointsServI.del2KP_index_Points(kp_index_points);
                                System.out.println("\n\n删除未被勾选的协会毕业要求\n\n");
                            }
                        }
                    }
                }
            }
            return new Result(Enums.Success, "修改成功");
        } else {
            return new Result(Enums.Null, "提交到服务器的json为null");
        }


    }

    @GetMapping("del")
    private Result deleletKnowPoint(Knowledge_Points knowledge_points) {
        System.out.println("删除的知识点id》" + knowledge_points.getTkp_id());
        knowledge_pointsServI.delKnowledge_Points(knowledge_points);
        return new Result(Enums.Success, "查询知识点id>" + knowledge_points.getTkp_id());
    }
}
