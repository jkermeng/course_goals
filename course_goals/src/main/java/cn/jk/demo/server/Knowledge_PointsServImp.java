package cn.jk.demo.server;

import cn.jk.demo.mapper.KP_index_PointsMapper;
import cn.jk.demo.mapper.Knowledge_PointsMapper;
import cn.jk.demo.mapper.Test_KP_IPMapper;
import cn.jk.demo.pojo.*;
import cn.jk.demo.pojo.uentity.Chapter;
import cn.jk.demo.pojo.uentity.DefaultKnowledgeMajor;
import cn.jk.demo.server.imp.Ass_MajorServI;
import cn.jk.demo.server.imp.Association_Graduation_RequirementsServI;
import cn.jk.demo.server.imp.Knowledge_PointsServI;
import cn.jk.demo.server.imp.Major_graduation_requirementsServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class Knowledge_PointsServImp implements Knowledge_PointsServI {
    @Autowired
    private Knowledge_PointsMapper knowledge_pointsMapper;
    @Autowired
    private KP_index_PointsMapper kp_index_pointsMapper;
    @Autowired
    private Major_graduation_requirementsServI major_graduation_requirementsServI;
    @Autowired
    private Association_Graduation_RequirementsServI association_graduation_requirementsServI;

    @Autowired
    private Ass_MajorServI ass_majorServI;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Chapter> showByCourseId(Knowledge_Points knowledge_points) {
        //有问题。。。。。
        List<Knowledge_Points> arrayList = new ArrayList();
        HashSet<Knowledge_Points> knowledge_points1 = knowledge_pointsMapper.selectsByKey(knowledge_points);//课程下的各个知识点
        arrayList.addAll(knowledge_points1);
        Collections.sort(arrayList, new Comparator<Knowledge_Points>() {
            @Override
            public int compare(Knowledge_Points o1, Knowledge_Points o2) {
                return o1.getTkp_part() - o2.getTkp_part();
            }
        });
        int charpnum = 1;
        int charp = 1;
        int lanth = arrayList.size();
        List<Chapter> chapterList = new ArrayList<>();//章节
        List<Knowledge_Points> knowledgeList = new ArrayList<>();
        Chapter chapter = new Chapter();//第一章节
        for (int i = 0; i < arrayList.size(); i++) {
            Knowledge_Points kp = arrayList.get(i);//每一个知识点
            List<Ass_Major> ass_majors = ass_majorServI.showAll();//毕业要求中框好的协会毕业要求
            //打包每个章节，和知识点数量
            if (kp.getTkp_part() == charp) {
                //知识点数量
                List<DefaultKnowledgeMajor> mrList = new ArrayList();
                List<Association_Graduation_Requirements> agrList = new ArrayList();
                KP_index_Points kp_index_points = new KP_index_Points();
                kp_index_points.setTable_knowledge_points_tkp_id(kp);
                List<KP_index_Points> list = new ArrayList();//每一个课程下对应的知识点的勾选的毕业要求和协会要求
                Set<KP_index_Points> kp_index_points1 = kp_index_pointsMapper.selectsByKey(kp_index_points);
                list.addAll(kp_index_points1);
                Collections.sort(list, new Comparator<KP_index_Points>() {
                    @Override
                    public int compare(KP_index_Points o1, KP_index_Points o2) {
                        return o1.getTip_kp_id() - o2.getTip_kp_id();
                    }
                });
                for (KP_index_Points kpin : list      //判断每一个知识点的指标是不是被勾选
                ) {
                    for (Ass_Major am :
                            ass_majors) {
                        //框定好的毕业要求和协会要求
                        if (am.getTable_major_graduation_requirements_tmgr_id().getTmgr_id() == kpin.getTable_major_g().getTmgr_id() && am.getTable_association_graduation_requirements_tagr_id().getTagr_id() == kpin.getTable_part()) {
                            Association_Graduation_Requirements association_graduation_requirements = new Association_Graduation_Requirements();
                            association_graduation_requirements.setTagr_id(kpin.getTable_part());
                            association_graduation_requirements.setIndex_points(am.getTable_association_graduation_requirements_tagr_id().getIndex_points());
                            association_graduation_requirements.setTagr_describe(am.getTable_association_graduation_requirements_tagr_id().getTagr_describe()+"");
                            association_graduation_requirements.setTagr_title(am.getTable_association_graduation_requirements_tagr_id().getTagr_title()+" ");
                            association_graduation_requirements.setCheckBox(true);
                            am.setTable_association_graduation_requirements_tagr_id(association_graduation_requirements);
                        }
                    }
                }
                int star = 1;
                int end = 0;
                int langh = ass_majors.size();
                DefaultKnowledgeMajor defaultKnowledgeMajor = new DefaultKnowledgeMajor();
                Major_graduation_requirements object = new Major_graduation_requirements();
                for (Ass_Major am : ass_majors
                ) {
                    end++;
                    if (am.getTable_major_graduation_requirements_tmgr_id().getTmgr_id() == star) {
                        //相同毕业要求的时候
                        object = am.getTable_major_graduation_requirements_tmgr_id();
                        agrList.add(am.getTable_association_graduation_requirements_tagr_id());
                        if (end == langh) {
                            defaultKnowledgeMajor.setMajor_graduation_requirements(object);
                            defaultKnowledgeMajor.setAssociation_Graduation_Requirements(agrList);
                            mrList.add(defaultKnowledgeMajor);//组装
                        }
                    } else {
                        //不同的毕业要求的时候
                        defaultKnowledgeMajor.setMajor_graduation_requirements(object);
                        defaultKnowledgeMajor.setAssociation_Graduation_Requirements(agrList);
                        mrList.add(defaultKnowledgeMajor);//组装
                        defaultKnowledgeMajor = new DefaultKnowledgeMajor();
                        agrList = new ArrayList();
                        object = new Major_graduation_requirements();
                        star = am.getTable_major_graduation_requirements_tmgr_id().getTmgr_id();
                        object = am.getTable_major_graduation_requirements_tmgr_id();
                        agrList.add(am.getTable_association_graduation_requirements_tagr_id());
                    }
                }

                kp.setDefaultKnowledgeMajors(mrList);


                chapter.setName("第" + charp + "章");
                chapter.setNum(charpnum);//知识点数量
                knowledgeList.add(kp);//同一章下的知识点
                if (lanth == i + 1) {
                    chapter.setPointsList(knowledgeList);
                    chapterList.add(chapter); //添加到list章节数组
                }

                charpnum++;
            } else {
                chapter.setPointsList(knowledgeList);//同一章下的所有知识点
                chapterList.add(chapter); //添加到list章节数组

                //知识点数量
                List<DefaultKnowledgeMajor> mrList = new ArrayList();//   可能有问题！！！！！！！
                Major_graduation_requirements object = new Major_graduation_requirements();
                List<Association_Graduation_Requirements> agrList = new ArrayList();

                KP_index_Points kp_index_points = new KP_index_Points();
                kp_index_points.setTable_knowledge_points_tkp_id(kp);
                List<KP_index_Points> list = new ArrayList();//每一个课程下对应的知识点的勾选的毕业要求和协会要求
                Set<KP_index_Points> kp_index_points1 = kp_index_pointsMapper.selectsByKey(kp_index_points);
                list.addAll(kp_index_points1);
                Collections.sort(list, new Comparator<KP_index_Points>() {
                    @Override
                    public int compare(KP_index_Points o1, KP_index_Points o2) {
                        return o1.getTip_kp_id() - o2.getTip_kp_id();
                    }
                });
                for (KP_index_Points kpin : list      //每一个知识点的指标是不是被勾选
                ) {
                    for (Ass_Major am :
                            ass_majors) {
                        //框定好的毕业要求和协会要求
                        if (am.getTable_major_graduation_requirements_tmgr_id().getTmgr_id() == kpin.getTable_major_g().getTmgr_id() && am.getTable_association_graduation_requirements_tagr_id().getTagr_id() == kpin.getTable_part()) {
                            Association_Graduation_Requirements association_graduation_requirements = new Association_Graduation_Requirements();
                            association_graduation_requirements.setTagr_id(kpin.getTable_part());
                            association_graduation_requirements.setIndex_points(am.getTable_association_graduation_requirements_tagr_id().getIndex_points());
                            association_graduation_requirements.setTagr_describe(am.getTable_association_graduation_requirements_tagr_id().getTagr_describe()+"");
                            association_graduation_requirements.setTagr_title(am.getTable_association_graduation_requirements_tagr_id().getTagr_title()+" ");
                            association_graduation_requirements.setCheckBox(true);
                            am.setTable_association_graduation_requirements_tagr_id(association_graduation_requirements);
                        }
                    }
                }
                int star = 1;
                int end = 0;
                int langh = ass_majors.size();
                DefaultKnowledgeMajor defaultKnowledgeMajor = new DefaultKnowledgeMajor();
                for (Ass_Major am : ass_majors
                ) {
                    end++;
                    if (am.getTable_major_graduation_requirements_tmgr_id().getTmgr_id() == star) {
                        //相同毕业要求的时候
                        object = am.getTable_major_graduation_requirements_tmgr_id();
                        agrList.add(am.getTable_association_graduation_requirements_tagr_id());
                        if (end == langh) {
                            defaultKnowledgeMajor.setMajor_graduation_requirements(object);
                            defaultKnowledgeMajor.setAssociation_Graduation_Requirements(agrList);
                            mrList.add(defaultKnowledgeMajor);//组装
                        }
                    } else {
                        //不同的毕业要求的时候
                        defaultKnowledgeMajor.setMajor_graduation_requirements(object);
                        defaultKnowledgeMajor.setAssociation_Graduation_Requirements(agrList);
                        mrList.add(defaultKnowledgeMajor);//组装
                        defaultKnowledgeMajor = new DefaultKnowledgeMajor();
                        agrList = new ArrayList();
                        object = new Major_graduation_requirements();
                        star = am.getTable_major_graduation_requirements_tmgr_id().getTmgr_id();
                        System.out.println(star);
                        object = am.getTable_major_graduation_requirements_tmgr_id();
                        agrList.add(am.getTable_association_graduation_requirements_tagr_id());
                    }
                }
                kp.setDefaultKnowledgeMajors(mrList);

                charp = kp.getTkp_part();
                charpnum = 1;
                chapter = new Chapter();//下一章
                knowledgeList = new ArrayList<>();
                chapter.setName("第" + charp + "章");
                chapter.setNum(charpnum);//知识点数量
                knowledgeList.add(kp);//同一章下的知识点
                if (lanth == i + 1) {
                    chapter.setPointsList(knowledgeList);
                    chapterList.add(chapter); //添加到list章节数组
                }
            }
        }
        return chapterList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Knowledge_Points> showKnowledge_PointsByKeys(Knowledge_Points knowledge_points) {
        HashSet<Knowledge_Points> knowledge_points1 = knowledge_pointsMapper.selectsByKey(knowledge_points);
        List<Knowledge_Points> knowledge_points2 = new ArrayList<>();
        knowledge_points2.addAll(knowledge_points1);
        Collections.sort(knowledge_points2, new Comparator<Knowledge_Points>() {
            @Override
            public int compare(Knowledge_Points o1, Knowledge_Points o2) {
                return o1.getTkp_id() - o2.getTkp_id();
            }
        });
        return knowledge_points2;
    }

    @Override
    public Knowledge_Points showByKnowledge_PointsId(Knowledge_Points knowledge_points) {
        return knowledge_pointsMapper.selectOneByKey(knowledge_points);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addKnowledge_Points(Knowledge_Points knowledge_points) {
        return knowledge_pointsMapper.insert(knowledge_points);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Knowledge_Points showOneByKeys(Knowledge_Points knowledge_points) {
        return knowledge_pointsMapper.selectOneByKey(knowledge_points);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateKnowledge_Points(Knowledge_Points knowledge_points) {
        return knowledge_pointsMapper.update(knowledge_points);
    }

    @Autowired
    private Test_KP_IPMapper test_kp_ipMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Integer delKnowledge_Points(Knowledge_Points knowledge_points) {
        Test_KP_IP test_kp_ip = new Test_KP_IP();
        test_kp_ip.setTtkp_kp(knowledge_points.getTkp_id());
        Integer delete = test_kp_ipMapper.delete(test_kp_ip);//table_test_kp_ip
        KP_index_Points kp_index_points = new KP_index_Points();
        kp_index_points.setTable_knowledge_points_tkp_id(knowledge_points);
        Integer delete1 = kp_index_pointsMapper.delete(kp_index_points);//table_kp_index_points
        return knowledge_pointsMapper.delete(knowledge_points);
    }
}
