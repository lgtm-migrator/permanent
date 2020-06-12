package com.qianxunclub.permanent.controller;

import com.qianxunclub.permanent.model.PostSubjectCategories;
import com.qianxunclub.permanent.model.Result;
import com.qianxunclub.permanent.model.SessionInfo;
import com.qianxunclub.permanent.model.SubjectCategoriesNode;
import com.qianxunclub.permanent.service.SubjectCategoriesService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbin
 */
@RestController
@RequestMapping("api/subject-categories")
@AllArgsConstructor
public class SubjectCategoriesController {

    private final SubjectCategoriesService subjectCategoriesService;

    @GetMapping("tree")
    public Result tree(
        @RequestAttribute SessionInfo sessionInfo
    ) {
        List<SubjectCategoriesNode> subjectCategoriesNodeList = subjectCategoriesService
            .tree(sessionInfo.getCustomersId());
        return Result.success(subjectCategoriesNodeList);
    }

    @PostMapping
    public Result post(
        @RequestAttribute SessionInfo sessionInfo,
        @RequestBody PostSubjectCategories postSubjectCategories
    ) {
        subjectCategoriesService
            .addSubjectCategories(sessionInfo.getCustomersId(), postSubjectCategories);
        return Result.success();
    }

}
