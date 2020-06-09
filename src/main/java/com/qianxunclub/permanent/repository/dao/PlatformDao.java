package com.qianxunclub.permanent.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianxunclub.permanent.repository.entity.PlatformEntity;
import com.qianxunclub.permanent.repository.mapper.PlatformMapper;
import com.qianxunclub.permanent.service.platform.data.PlatformOauth;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
@AllArgsConstructor
public class PlatformDao {

    private final PlatformMapper platformMapper;

    public PlatformEntity insertOrUpdate(PlatformOauth platformOauth) {
        PlatformEntity platformEntity = this
            .selectByOpenId(platformOauth.getPlatform(), platformOauth.getOpenId());
        if (platformEntity == null) {
            platformEntity = new PlatformEntity();
            platformEntity.setPlatform(platformOauth.getPlatform());
            platformEntity.setOpenId(platformOauth.getOpenId());
        }
        platformEntity.setToken(platformOauth.getAccessToken());
        platformEntity.setExpiresIn(platformOauth.getExpiresIn());
        platformEntity.setRefreshToken(platformOauth.getRefreshToken());
        platformEntity.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        if (ObjectUtils.isEmpty(platformEntity.getId())) {
            platformEntity.setCreateAt(new Timestamp(System.currentTimeMillis()));
            platformMapper.insert(platformEntity);
        } else {
            platformMapper.updateById(platformEntity);
        }

        return platformEntity;
    }

    public PlatformEntity selectByOpenId(String platform, String openId) {
        QueryWrapper<PlatformEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("platform", platform);
        queryWrapper.eq("open_id", openId);
        return platformMapper.selectOne(queryWrapper);
    }


}
