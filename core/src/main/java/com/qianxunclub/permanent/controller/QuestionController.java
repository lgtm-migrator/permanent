package com.qianxunclub.permanent.controller;


import com.qianxunclub.permanent.model.PostQuestion;
import com.qianxunclub.permanent.model.Result;
import com.qianxunclub.permanent.model.SessionInfo;
import com.qianxunclub.permanent.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbin
 */
@RestController
@RequestMapping("api/question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public Result get(
        @RequestAttribute SessionInfo sessionInfo
    ) {
        return Result
            .success(questionService.list(sessionInfo.getCustomersId()));
    }

    @GetMapping("{subjectCategoriesId}")
    public Result get(
        @RequestAttribute SessionInfo sessionInfo,
        @PathVariable Long subjectCategoriesId
    ) {
        return Result
            .success(questionService.list(sessionInfo.getCustomersId(), subjectCategoriesId));
    }

    @PostMapping
    public Result post(
        @RequestAttribute SessionInfo sessionInfo,
        @RequestBody PostQuestion postQuestion
    ) {
        return Result
            .success(questionService.createQuestion(sessionInfo.getCustomersId(), postQuestion));
    }

}
