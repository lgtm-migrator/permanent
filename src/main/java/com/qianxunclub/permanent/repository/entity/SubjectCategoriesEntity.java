package com.qianxunclub.permanent.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Timestamp;
import lombok.Data;

/**
 * @author zhangbin
 */
@Data
@TableName("subject_categories")
public class SubjectCategoriesEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long customersId;
    private String categoriesName;
    private Long parentId;
    private Long orderNumber;
    private Timestamp updateAt;
    private Timestamp createAt;
}
