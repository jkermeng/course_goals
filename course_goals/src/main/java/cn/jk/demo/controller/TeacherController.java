package cn.jk.demo.controller;

import cn.jk.demo.enums.Enums;
import cn.jk.demo.enums.Result;
import cn.jk.demo.pojo.Ass_Major;
import cn.jk.demo.pojo.Course;
import cn.jk.demo.pojo.Knowledge_Points;
import cn.jk.demo.pojo.rootTest.ArrayBrowser;
import cn.jk.demo.pojo.rootTest.RootOne;
import cn.jk.demo.pojo.uentity.Chapter;
import cn.jk.demo.pojo.uentity.KP_Index_Data;
import cn.jk.demo.server.imp.CourseServI;
import cn.jk.demo.server.imp.Knowledge_PointsServI;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {
    @Autowired
    private CourseServI courseServI;
    @Autowired
    private Knowledge_PointsServI knowledge_pointsServI;

    @GetMapping("test1")
    private Result test1() {
        Map<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("key1", null);
        stringStringHashMap.put("key2", "value2");
        Knowledge_Points knowledge_points = new Knowledge_Points();
        Course course = new Course();
        course.setTc_id(1);
        knowledge_points.setTable_course_tc_id(course);
        List<Chapter> chapters = knowledge_pointsServI.showByCourseId(knowledge_points);
        return new Result(Enums.Success, chapters);
    }

    @GetMapping("test2")
    private KP_Index_Data[] test2(KP_Index_Data[] kp_index_data) {
        for (KP_Index_Data kpd : kp_index_data
        ) {
            System.out.println(kpd);
        }

        return kp_index_data;
    }

    @PostMapping(value = "test3")
    private String test3(@RequestBody RootOne rootOne) {
        System.out.println(rootOne);
        System.out.println(rootOne.getAddress());
        for (ArrayBrowser ab : rootOne.getArrayBrowser()
        ) {
            System.out.println(ab);
        }
        return "rootOne";
    }
}
