package com.qianxunclub.permanent.service;

import com.qianxunclub.permanent.model.PostQuestion;
import com.qianxunclub.permanent.repository.dao.QuestionDao;
import com.qianxunclub.permanent.repository.entity.QuestionEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhangbin
 */
@Slf4j
@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionDao questionDao;

    public List<QuestionEntity> list(Long customersId) {
        return questionDao.list(customersId);
    }

    public List<QuestionEntity> list(Long customersId, Long subjectCategoriesId) {
        return questionDao.list(customersId, subjectCategoriesId);
    }

    public QuestionEntity createQuestion(Long customersId, PostQuestion postQuestion) {
        QuestionEntity questionEntity = new QuestionEntity();
        return questionEntity;
    }


}
