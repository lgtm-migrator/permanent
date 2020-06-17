package com.qianxunclub.permanent.repository.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianxunclub.permanent.repository.entity.QuestionEntity;
import com.qianxunclub.permanent.repository.mapper.QuestionMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author zhangbin
 */
@Repository
@AllArgsConstructor
public class QuestionDao {

    private final QuestionMapper questionMapper;

    /**
     * 获取问题列表
     *
     * @param customersId
     * @return
     */
    public List<QuestionEntity> list(Long customersId) {
        return this.list(customersId, null);
    }

    /**
     * 获取问题列表
     *
     * @param customersId
     * @param subjectCategoriesId 不必填
     * @return
     */
    public List<QuestionEntity> list(Long customersId, Long subjectCategoriesId) {
        QueryWrapper<QuestionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customers_id", customersId);
        if (subjectCategoriesId != null) {
            queryWrapper.eq("subject_categories_id", subjectCategoriesId);
        }
        return questionMapper.selectList(queryWrapper);
    }

    public void refreshOrderNumber(Long customersId, Long subjectCategoriesId, Long orderNumber) {
        questionMapper.refreshOrderNumber(customersId, subjectCategoriesId, orderNumber);
    }
}
