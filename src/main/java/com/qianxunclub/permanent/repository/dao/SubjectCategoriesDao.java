package com.qianxunclub.permanent.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianxunclub.permanent.repository.entity.SubjectCategoriesEntity;
import com.qianxunclub.permanent.repository.mapper.SubjectCategoriesMapper;
import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author zhangbin
 */
@Repository
@AllArgsConstructor
public class SubjectCategoriesDao {

    private SubjectCategoriesMapper subjectCategoriesMapper;

    public SubjectCategoriesEntity get(Long customersId, Long subjectCategoriesId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", subjectCategoriesId);
        queryWrapper.eq("customers_id", customersId);
        return subjectCategoriesMapper.selectOne(queryWrapper);
    }

    public boolean isExistOrderNumber(Long customersId, Long orderNumber) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("customers_id", customersId);
        queryWrapper.eq("order_number", orderNumber);
        return subjectCategoriesMapper.selectCount(queryWrapper) > 0;
    }

    public List<SubjectCategoriesEntity> getRootNodes(Long customersId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("customers_id", customersId);
        queryWrapper.isNull("parent_id");
        queryWrapper.orderByAsc("order_number");
        return subjectCategoriesMapper.selectList(queryWrapper);
    }

    public List<SubjectCategoriesEntity> getChildNodes(Long customersId, Long parentId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("customers_id", customersId);
        queryWrapper.eq("parent_id", parentId);
        queryWrapper.orderByAsc("order_number");
        return subjectCategoriesMapper.selectList(queryWrapper);
    }

    public SubjectCategoriesEntity addNode(SubjectCategoriesEntity subjectCategoriesEntity) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        subjectCategoriesEntity.setUpdateAt(timestamp);
        subjectCategoriesEntity.setCreateAt(timestamp);
        subjectCategoriesMapper.insert(subjectCategoriesEntity);
        return subjectCategoriesEntity;
    }

    public void refreshOrderNumber(Long customersId, Long parentId, Long orderNumber) {
        subjectCategoriesMapper.refreshOrderNumber(customersId, parentId, orderNumber);
    }

}
