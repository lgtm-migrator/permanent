package com.qianxunclub.permanent.model;

import lombok.Data;

/**
 * @author zhangbin
 */
@Data
public class PostQuestion {

    private String title;
    private String editType;
    private String questionsContent;
    private String answerContent;
    private Long orderNumber;

}
