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
@TableName("answer")
public class AnswerEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long customersId;
    private String sourceContent;
    private String showContent;
    private Timestamp updateAt;
    private Timestamp createAt;
}
