package com.honeysense.magpie.user.service;

import com.honeysense.magpie.framework.saas.service.MagpieUserOneService;
import com.honeysense.magpie.framework.entity.MagpiePage;
import com.honeysense.magpie.user.entity.UserRelation;

public interface UserRelationService extends MagpieUserOneService<UserRelation> {
    MagpiePage<UserRelation> findAllByDirectInvitorUserId(Long directInvitorUserId, int page, int size);
    MagpiePage<UserRelation> findAllByIndirectInvitorUserId(Long indirectInvitorUserId, int page, int size);
}
