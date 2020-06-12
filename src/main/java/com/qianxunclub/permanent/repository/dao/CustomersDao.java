package com.qianxunclub.permanent.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianxunclub.permanent.repository.entity.CustomersEntity;
import com.qianxunclub.permanent.repository.mapper.CustomersMapper;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CustomersDao {

    private final CustomersMapper customersMapper;

    public CustomersEntity insert(CustomersEntity customersEntity) {
        customersEntity.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        customersEntity.setCreateAt(new Timestamp(System.currentTimeMillis()));
        customersMapper.insert(customersEntity);
        return customersEntity;
    }

    public CustomersEntity getById(Long id) {
        return customersMapper.selectById(id);
    }

    public CustomersEntity getByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        return customersMapper.selectOne(queryWrapper);
    }

}
