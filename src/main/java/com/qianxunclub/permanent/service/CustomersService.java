package com.qianxunclub.permanent.service;

import com.qianxunclub.permanent.repository.dao.CustomersBindingDao;
import com.qianxunclub.permanent.repository.dao.CustomersDao;
import com.qianxunclub.permanent.repository.dao.PlatformDao;
import com.qianxunclub.permanent.repository.entity.CustomersBindingEntity;
import com.qianxunclub.permanent.repository.entity.CustomersEntity;
import com.qianxunclub.permanent.repository.entity.PlatformEntity;
import com.qianxunclub.permanent.service.platform.data.PlatformUserInfo;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersService {

    private final CustomersBindingDao customersBindingDao;
    private final CustomersDao customersDao;

    public CustomersBindingEntity register(PlatformUserInfo platformUserInfo,
        PlatformEntity platformEntity) {
        CustomersBindingEntity customersBindingEntity = customersBindingDao
            .get(platformEntity.getId());
        if (customersBindingEntity == null) {
            CustomersEntity customersEntity = this.generateCustomers(platformUserInfo);
            customersDao.insert(customersEntity);
            customersBindingEntity = new CustomersBindingEntity();
            customersBindingEntity.setCustomersId(customersEntity.getId());
            customersBindingEntity.setPlatformId(platformEntity.getId());
            customersBindingDao.insert(customersBindingEntity);
        }
        return customersBindingEntity;
    }

    public CustomersEntity generateCustomers(PlatformUserInfo platformUserInfo) {
        CustomersEntity customersEntity = new CustomersEntity();
        customersEntity.setUsername(UUID.randomUUID().toString());
        customersEntity.setNickname(platformUserInfo.getNickname());
        customersEntity.setPhone(platformUserInfo.getPhone());
        customersEntity.setEmail(platformUserInfo.getEmail());
        customersEntity.setGender(platformUserInfo.getGender());
        customersEntity.setAvatarUrl(platformUserInfo.getAvatarUrl());
        return customersEntity;
    }

}
