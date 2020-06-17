package com.qianxunclub.permanent.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianxunclub.permanent.repository.entity.SubjectCategoriesQuestionEntity;
import com.qianxunclub.permanent.repository.mapper.SubjectCategoriesQuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangbin
 */
@Repository
@AllArgsConstructor
public class SubjectCategoriesQuestionDao {

    private final SubjectCategoriesQuestionMapper subjectCategoriesQuestionMapper;

    @Transactional(rollbackFor = {Exception.class})
    public SubjectCategoriesQuestionEntity insertAndRefreshOrderNumber(
        SubjectCategoriesQuestionEntity subjectCategoriesQuestionEntity) {
        Long orderNumber;
        if (subjectCategoriesQuestionEntity.getOrderNumber() != null && this
            .isExistOrderNumber(subjectCategoriesQuestionEntity.getCustomersId(),
                subjectCategoriesQuestionEntity.getSubjectCategoriesId(),
                subjectCategoriesQuestionEntity.getOrderNumber())) {
            orderNumber = subjectCategoriesQuestionEntity.getOrderNumber();
            this.refreshOrderNumber(subjectCategoriesQuestionEntity.getCustomersId(),
                subjectCategoriesQuestionEntity.getSubjectCategoriesId(), orderNumber);
        } else {
            orderNumber = this.maxOrderNumber(subjectCategoriesQuestionEntity.getCustomersId(),
                subjectCategoriesQuestionEntity.getSubjectCategoriesId());
        }
        subjectCategoriesQuestionEntity.setOrderNumber(orderNumber);
        subjectCategoriesQuestionMapper.insert(subjectCategoriesQuestionEntity);
        return subjectCategoriesQuestionEntity;
    }

    public boolean isExistOrderNumber(Long customersId, Long subjectCategoriesId,
        Long orderNumber) {
        QueryWrapper<SubjectCategoriesQuestionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customers_id", customersId);
        queryWrapper.eq("subject_categories_id", subjectCategoriesId);
        queryWrapper.eq("order_number", orderNumber);
        return subjectCategoriesQuestionMapper.selectCount(queryWrapper) > 0;
    }

    public Long maxOrderNumber(Long customersId, Long subjectCategoriesId) {
        QueryWrapper<SubjectCategoriesQuestionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customers_id", customersId);
        queryWrapper.eq("subject_categories_id", subjectCategoriesId);
        queryWrapper.orderByAsc("order_number");
        SubjectCategoriesQuestionEntity subjectCategoriesQuestionEntity = subjectCategoriesQuestionMapper
            .selectOne(queryWrapper);
        return subjectCategoriesQuestionEntity == null ? null
            : subjectCategoriesQuestionEntity.getOrderNumber();
    }

    public void refreshOrderNumber(Long customersId, Long subjectCategoriesId, Long orderNumber) {
        subjectCategoriesQuestionMapper
            .refreshOrderNumber(customersId, subjectCategoriesId, orderNumber);
    }

}
