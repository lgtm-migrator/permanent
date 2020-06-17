package com.qianxunclub.permanent.controller;

import com.qianxunclub.permanent.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbin
 */
@RestController
@RequestMapping("api/answer")
@AllArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

}
