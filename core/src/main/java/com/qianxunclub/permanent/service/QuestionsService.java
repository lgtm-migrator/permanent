package com.qianxunclub.permanent.service;

import com.qianxunclub.permanent.repository.dao.QuestionsDao;
import com.qianxunclub.permanent.repository.entity.QuestionsEntity;
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
public class QuestionsService {

    private final QuestionsDao questionsDao;

    public List<QuestionsEntity> list(Long customersId) {
        return questionsDao.list(customersId);
    }

    public List<QuestionsEntity> list(Long customersId, Long subjectCategoriesId) {
        return questionsDao.list(customersId, subjectCategoriesId);
    }


}
