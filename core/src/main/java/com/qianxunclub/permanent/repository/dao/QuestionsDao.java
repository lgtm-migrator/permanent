package com.qianxunclub.permanent.repository.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianxunclub.permanent.repository.entity.QuestionsEntity;
import com.qianxunclub.permanent.repository.mapper.QuestionsMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author zhangbin
 */
@Repository
@AllArgsConstructor
public class QuestionsDao {

    private final QuestionsMapper questionsMapper;

    /**
     * 获取问题列表
     *
     * @param customersId
     * @return
     */
    public List<QuestionsEntity> list(Long customersId) {
        return this.list(customersId, null);
    }

    /**
     * 获取问题列表
     *
     * @param customersId
     * @param subjectCategoriesId 不必填
     * @return
     */
    public List<QuestionsEntity> list(Long customersId, Long subjectCategoriesId) {
        QueryWrapper<QuestionsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customers_id", customersId);
        if (subjectCategoriesId != null) {
            queryWrapper.eq("subject_categories_id", subjectCategoriesId);
        }
        return questionsMapper.selectList(queryWrapper);
    }

    public void refreshOrderNumber(Long customersId, Long subjectCategoriesId, Long orderNumber) {
        questionsMapper.refreshOrderNumber(customersId, subjectCategoriesId, orderNumber);
    }
}
