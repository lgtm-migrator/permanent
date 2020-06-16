package com.qianxunclub.permanent.controller;


import com.qianxunclub.permanent.model.Result;
import com.qianxunclub.permanent.model.SessionInfo;
import com.qianxunclub.permanent.service.QuestionsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbin
 */
@RestController
@RequestMapping("api/questions")
@AllArgsConstructor
public class QuestionsController {

    private final QuestionsService questionsService;

    @GetMapping
    public Result get(
        @RequestAttribute SessionInfo sessionInfo
    ) {
        return Result
            .success(questionsService.list(sessionInfo.getCustomersId()));
    }

    @GetMapping("{subjectCategoriesId}")
    public Result get(
        @RequestAttribute SessionInfo sessionInfo,
        @PathVariable Long subjectCategoriesId
    ) {
        return Result
            .success(questionsService.list(sessionInfo.getCustomersId(), subjectCategoriesId));
    }

}
