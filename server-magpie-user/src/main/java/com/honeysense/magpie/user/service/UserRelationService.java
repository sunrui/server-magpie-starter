package com.honeysense.magpie.user.service;

import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.service.MagpieUserOneService;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.user.entity.UserRelation;

public interface UserRelationService extends MagpieUserOneService<UserRelation> {
    MagpiePage<UserRelation> findAllByDirectInvitorUserId(Long directInvitorUserId, MagpiePageRequest magpiePageRequest);
    MagpiePage<UserRelation> findAllByIndirectInvitorUserId(Long indirectInvitorUserId, MagpiePageRequest magpiePageRequest);
}
