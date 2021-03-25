package com.honeysense.media.repository;

import com.honeysense.media.entity.DriverOrder;
import com.honeysense.media.entity.DriverOrderStatus;
import com.honeysense.magpie.saas.repository.MagpieChannelUserManyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface DriverOrderRepository extends MagpieChannelUserManyRepository<DriverOrder> {
    Page<DriverOrder> findAllByChannelIdAndUserIdAndStatusNotIn(@NotNull Long channelId, @NotNull Long userId, Collection<@NotNull DriverOrderStatus> statuses, Pageable pageable);
}
