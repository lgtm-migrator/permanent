package com.qianxunclub.permanent.service;

import com.qianxunclub.permanent.model.PostQuestion;
import com.qianxunclub.permanent.repository.dao.AnswerDao;
import com.qianxunclub.permanent.repository.dao.QuestionDao;
import com.qianxunclub.permanent.repository.dao.SubjectCategoriesQuestionDao;
import com.qianxunclub.permanent.repository.entity.AnswerEntity;
import com.qianxunclub.permanent.repository.entity.QuestionEntity;
import com.qianxunclub.permanent.repository.entity.SubjectCategoriesQuestionEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangbin
 */
@Slf4j
@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionDao questionDao;
    private final AnswerDao answerDao;
    private final SubjectCategoriesQuestionDao subjectCategoriesQuestionDao;

    public List<QuestionEntity> list(Long customersId) {
        return questionDao.list(customersId);
    }

    public List<QuestionEntity> list(Long customersId, Long subjectCategoriesId) {
        return questionDao.list(customersId, subjectCategoriesId);
    }

    @Transactional(rollbackFor = {Exception.class})
    public QuestionEntity createQuestion(Long customersId, PostQuestion postQuestion) {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setCustomersId(customersId);
        answerEntity.setSourceContent(postQuestion.getAnswerContent());
        answerDao.insert(answerEntity);

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setCustomersId(customersId);
        questionEntity.setTitle(postQuestion.getTitle());
        questionEntity.setSourceContent(postQuestion.getQuestionsContent());
        questionEntity.setAnswerId(answerEntity.getId());
        questionDao.insert(questionEntity);

        SubjectCategoriesQuestionEntity subjectCategoriesQuestionEntity = new SubjectCategoriesQuestionEntity();
        subjectCategoriesQuestionEntity.setCustomersId(customersId);
        subjectCategoriesQuestionEntity.setQuestionId(questionEntity.getId());
        subjectCategoriesQuestionEntity
            .setSubjectCategoriesId(postQuestion.getSubjectCategoriesId());
        subjectCategoriesQuestionEntity.setOrderNumber(postQuestion.getOrderNumber());
        subjectCategoriesQuestionDao.insertAndRefreshOrderNumber(subjectCategoriesQuestionEntity);
        return questionEntity;
    }


}
