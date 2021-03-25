package com.honeysense.magpie.user.repository;

import com.honeysense.magpie.framework.saas.repository.MagpieUserOneRepository;
import com.honeysense.magpie.user.entity.UserRelation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRelationRepository extends MagpieUserOneRepository<UserRelation> {
    UserRelation findByUserId(Long userId);
    Page<UserRelation> findAllByDirectInvitorUserId(Long directInvitorUserId, Pageable pageable);
    Page<UserRelation> findAllByIndirectInvitorUserId(Long indirectInvitorUserId, Pageable pageable);
}
