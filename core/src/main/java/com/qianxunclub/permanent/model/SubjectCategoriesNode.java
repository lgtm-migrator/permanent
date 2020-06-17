package com.qianxunclub.permanent.model;

import com.qianxunclub.permanent.repository.entity.SubjectCategoriesEntity;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangbin
 */
@Data
public class SubjectCategoriesNode {

    private Long id;
    private Long customersId;
    private String categoriesName;
    private Long parentId;
    private Long orderNumber;
    private Timestamp updateAt;
    private Timestamp createAt;

    private List<SubjectCategoriesNode> chileNodes = new ArrayList<>();

    public void setChileNodes(List<SubjectCategoriesEntity> subjectCategoriesEntityList) {
        subjectCategoriesEntityList.forEach(subjectCategoriesEntity -> {
            SubjectCategoriesNode subjectCategoriesNode = new SubjectCategoriesNode();
            BeanUtils.copyProperties(subjectCategoriesEntity, subjectCategoriesNode);
            this.chileNodes.add(subjectCategoriesNode);
        });
    }

}
