package cn.jk.demo.server;

import cn.jk.demo.mapper.Test_Stu_ScoreMapper;
import cn.jk.demo.pojo.Test_Stu_Score;
import cn.jk.demo.server.imp.Test_Stu_ScoreServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class Test_Stu_ScoreServImp implements Test_Stu_ScoreServI {
    @Autowired
    private Test_Stu_ScoreMapper test_stu_scoreMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Test_Stu_Score> showStuByIdTSS(Test_Stu_Score test_stu_score) {
        List arrayList = new ArrayList();
        Set<Test_Stu_Score> test_stu_scores = test_stu_scoreMapper.selectsByKey(test_stu_score);
        arrayList.addAll(test_stu_scores);
        return arrayList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addTest_Stu_Score(Test_Stu_Score test_stu_score) {
        return test_stu_scoreMapper.insert(test_stu_score);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateTest_Stu_Score(Test_Stu_Score test_stu_score) {
        return test_stu_scoreMapper.update(test_stu_score);
    }
}
