package com.honeysense.magpie.user.service.impl;

import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.service.impl.MagpieUserOneServiceImpl;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.user.entity.UserRelation;
import com.honeysense.magpie.user.repository.UserRelationRepository;
import com.honeysense.magpie.user.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRelationServiceImpl extends MagpieUserOneServiceImpl<UserRelation> implements UserRelationService {
    private final UserRelationRepository userRelationRepository;

    @Autowired
    public UserRelationServiceImpl(UserRelationRepository userRelationRepository) {
        super(userRelationRepository);

        this.userRelationRepository = userRelationRepository;
    }

    @Override
    public MagpiePage<UserRelation> findAllByDirectInvitorUserId(Long directInvitorUserId, MagpiePageRequest magpiePageRequest) {
        if (!MagpieValidator.longId(directInvitorUserId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "directInvitorUserId");
        }

        MagpieValidator.object(magpiePageRequest);

        Page<UserRelation> elements = userRelationRepository.findAllByDirectInvitorUserId(directInvitorUserId, magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }

    @Override
    public MagpiePage<UserRelation> findAllByIndirectInvitorUserId(Long indirectInvitorUserId, MagpiePageRequest magpiePageRequest) {
        if (!MagpieValidator.longId(indirectInvitorUserId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "indirectInvitorUserId");
        }

        MagpieValidator.object(magpiePageRequest);

        Page<UserRelation> elements = userRelationRepository.findAllByIndirectInvitorUserId(indirectInvitorUserId, magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }
}
