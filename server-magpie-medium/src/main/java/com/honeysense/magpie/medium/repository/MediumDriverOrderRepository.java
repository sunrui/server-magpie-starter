package com.honeysense.magpie.medium.repository;

import com.honeysense.magpie.medium.entity.MediumDriverOrder;
import com.honeysense.magpie.medium.entity.MediumDriverOrderStatus;
import com.honeysense.magpie.framework.saas.repository.MagpieAppUserManyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface MediumDriverOrderRepository extends MagpieAppUserManyRepository<MediumDriverOrder> {
    Page<MediumDriverOrder> findAllByAppIdAndUserIdAndStatusNotIn(@NotNull Long appId, @NotNull Long userId, Collection<@NotNull MediumDriverOrderStatus> statuses, Pageable pageable);
}
