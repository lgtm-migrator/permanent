package com.qianxunclub.permanent.model;

import lombok.Data;

/**
 * @author zhangbin
 */
@Data
public class PostSubjectCategories {

    private Long subjectCategoriesId;
    private String categoriesName;
    private Long parentId;
    private Long orderNumber;
}
