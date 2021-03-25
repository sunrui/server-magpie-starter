package com.honeysense.user.service;

import com.honeysense.magpie.saas.service.MagpieUserOneService;
import com.honeysense.magpie.entity.MagpiePage;
import com.honeysense.user.entity.UserRelation;

public interface UserRelationService extends MagpieUserOneService<UserRelation> {
    MagpiePage<UserRelation> findAllByDirectInvitorUserId(Long directInvitorUserId, int page, int size);
    MagpiePage<UserRelation> findAllByIndirectInvitorUserId(Long indirectInvitorUserId, int page, int size);
}
