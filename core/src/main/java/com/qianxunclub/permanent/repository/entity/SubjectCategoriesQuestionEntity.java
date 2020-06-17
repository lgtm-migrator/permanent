package com.qianxunclub.permanent.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhangbin
 */
@Data
@TableName("subject_categories_question")
public class SubjectCategoriesQuestionEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long customersId;
    private Long subjectCategoriesId;
    private Long questionId;
    private Long orderNumber;

}
