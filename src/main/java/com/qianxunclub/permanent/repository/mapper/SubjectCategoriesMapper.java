package com.qianxunclub.permanent.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qianxunclub.permanent.repository.entity.SubjectCategoriesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhangbin
 */
@Mapper
public interface SubjectCategoriesMapper extends BaseMapper<SubjectCategoriesEntity> {

    @Update("update subject_categories set order_number = order_number + 1 where customers_id = #{customersId} and order_number >= #{orderNumber} and parent_id = #{parentId}")
    int refreshOrderNumber(@Param("customersId") Long customersId, @Param("parentId") Long parentId,
        @Param("orderNumber") Long orderNumber);

}
