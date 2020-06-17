package com.qianxunclub.permanent.repository.dao;

import com.qianxunclub.permanent.repository.mapper.AnswerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author zhangbin
 */
@Repository
@AllArgsConstructor
public class AnswerDao {

    private final AnswerMapper answerMapper;

}
