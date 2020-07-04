package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Comparator;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Ass_Major implements Serializable {
    private Major_graduation_requirements table_major_graduation_requirements_tmgr_id;//毕业要求
    private Association_Graduation_Requirements table_association_graduation_requirements_tagr_id;//协会毕业要求

    public Major_graduation_requirements getTable_major_graduation_requirements_tmgr_id() {
        return table_major_graduation_requirements_tmgr_id;
    }

    public void setTable_major_graduation_requirements_tmgr_id(Major_graduation_requirements table_major_graduation_requirements_tmgr_id) {
        this.table_major_graduation_requirements_tmgr_id = table_major_graduation_requirements_tmgr_id;
    }

    public Association_Graduation_Requirements getTable_association_graduation_requirements_tagr_id() {
        return table_association_graduation_requirements_tagr_id;
    }

    public void setTable_association_graduation_requirements_tagr_id(Association_Graduation_Requirements table_association_graduation_requirements_tagr_id) {
        this.table_association_graduation_requirements_tagr_id = table_association_graduation_requirements_tagr_id;
    }

}
