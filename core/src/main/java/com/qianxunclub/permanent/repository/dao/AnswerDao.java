package com.qianxunclub.permanent.repository.dao;

import com.qianxunclub.permanent.repository.entity.AnswerEntity;
import com.qianxunclub.permanent.repository.mapper.AnswerMapper;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author zhangbin
 */
@Repository
@AllArgsConstructor
public class AnswerDao {

    private final AnswerMapper answerMapper;

    public AnswerEntity insert(AnswerEntity answerEntity) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        answerEntity.setCreateAt(timestamp);
        answerEntity.setUpdateAt(timestamp);
        answerMapper.insert(answerEntity);
        return answerEntity;
    }

}
