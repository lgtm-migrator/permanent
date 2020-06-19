package com.qianxunclub.permanent.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianxunclub.permanent.repository.entity.CustomersBindingEntity;
import com.qianxunclub.permanent.repository.mapper.CustomersBindingMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author zhangbin
 */
@Repository
@AllArgsConstructor
public class CustomersBindingDao {

    private final CustomersBindingMapper customersBindingMapper;

    public CustomersBindingEntity getByPlatformId(Long platformId) {
        QueryWrapper<CustomersBindingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("platform_id", platformId);
        return customersBindingMapper.selectOne(queryWrapper);
    }

    public CustomersBindingEntity getByCustomersId(String platform, Long customersId) {
        QueryWrapper<CustomersBindingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customers_id", customersId);
        queryWrapper.eq("platform", platform);
        return customersBindingMapper.selectOne(queryWrapper);
    }


    public CustomersBindingEntity insert(CustomersBindingEntity customersBindingEntity) {
        customersBindingMapper.insert(customersBindingEntity);
        return customersBindingEntity;
    }

}
