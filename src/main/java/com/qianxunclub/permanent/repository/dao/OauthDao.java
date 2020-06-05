package com.qianxunclub.permanent.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianxunclub.permanent.repository.entity.OauthEntity;
import com.qianxunclub.permanent.repository.mapper.OauthMapper;
import com.qianxunclub.permanent.service.auth.data.OauthToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
@AllArgsConstructor
public class OauthDao {

    private final OauthMapper oauthMapper;

    public OauthEntity insertOrUpdate(OauthToken oauthToken) {
        OauthEntity oauthEntity = this
            .selectByOpenId(oauthToken.getPlatform(), oauthToken.getOpenId());
        if (oauthEntity == null) {
            oauthEntity = new OauthEntity();
        }
        oauthEntity.setPlatform(oauthEntity.getPlatform());
        oauthEntity.setToken(oauthToken.getAccessToken());
        oauthToken.setExpiresIn(oauthEntity.getExpiresIn());
        oauthToken.setRefreshToken(oauthToken.getRefreshToken());
        if (ObjectUtils.isEmpty(oauthEntity.getId())) {
            oauthMapper.insert(oauthEntity);
        } else {
            oauthMapper.updateById(oauthEntity);
        }

        return oauthEntity;
    }

    public OauthEntity selectByOpenId(String platform, String openId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("platform", platform);
        queryWrapper.eq("open_id", openId);
        return oauthMapper.selectOne(queryWrapper);
    }


}
