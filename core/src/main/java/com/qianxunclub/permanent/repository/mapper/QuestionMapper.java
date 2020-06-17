package com.qianxunclub.permanent.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qianxunclub.permanent.repository.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhangbin
 */
@Mapper
public interface QuestionMapper extends BaseMapper<QuestionEntity> {

    /**
     * 刷新排序
     *
     * @param customersId
     * @param subjectCategoriesId
     * @param orderNumber
     */
    @Update("update questions set order_number = order_number + 1 where customers_id = #{customersId} and order_number >= #{orderNumber} and subject_categories_id = #{subjectCategoriesId}")
    void refreshOrderNumber(@Param("customersId") Long customersId,
        @Param("subjectCategoriesId") Long subjectCategoriesId,
        @Param("orderNumber") Long orderNumber);
}
