package com.qianxunclub.permanent.service;


import com.qianxunclub.permanent.repository.dao.AnswerDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author zhangbin
 */
@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerDao answerDao;

}
