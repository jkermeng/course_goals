package cn.jk.demo.util;

import cn.jk.demo.mapper.TestMapper;
import cn.jk.demo.mapper.UserMapper;
import cn.jk.demo.pojo.uentity.UploadStuScore;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class UExcel {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private UserMapper userMapper;

    public UploadStuScore excel(InputStream inputStream) throws IOException, BiffException {
        Workbook workbook = Workbook.getWorkbook(inputStream);
        Sheet[] sheets = workbook.getSheets();
        int total = 0;
        UploadStuScore uploadStuScore = new UploadStuScore();
        for (Sheet sheet : sheets
        ) {
            int columns = sheet.getColumns();
            //由上至下，由左到右
            Integer goalt[] = new Integer[5];
            int i = 0;
            for (int j = 0; j < columns; j++) {
                if (j == 0) {
                    uploadStuScore.setTestName(sheet.getCell(j, 2).getContents());//试卷名称
                } else if (j == 1) {
                    uploadStuScore.setStuNumber(sheet.getCell(j, 2).getContents());//学生学号
                } else if (j == 2) {
                    uploadStuScore.setName(sheet.getCell(j, 2).getContents());//学生姓名
                } else if (j >= 3 && j < columns-1) {
                    goalt[i] = Integer.valueOf(sheet.getCell(j, 2).getContents());
                    i++;
                } else if (j == columns-1) {
                    uploadStuScore.setEveryScore(goalt);
                    for (int er:goalt
                         ) {
                        total+=er;
                    }
                    if (total==Integer.valueOf(sheet.getCell(j, 2).getContents())){
                        uploadStuScore.setScoreTotal(Integer.valueOf(sheet.getCell(j, 2).getContents()));//总成绩
                    }else {
                        uploadStuScore.setScoreTotal(total);//总成绩
                    }
                }
            }

        }
        return uploadStuScore;
    }


}
