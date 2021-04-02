package com.honeysense.magpie.user.repository;

import com.honeysense.magpie.framework.saas.repository.MagpieRepository;
import com.honeysense.magpie.user.entity.User;
import com.honeysense.magpie.user.entity.UserDeleted;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserDeletedRepository extends MagpieRepository<UserDeleted> {
}
