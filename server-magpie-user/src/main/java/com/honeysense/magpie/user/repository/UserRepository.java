package com.honeysense.magpie.user.repository;

import com.honeysense.magpie.framework.saas.repository.MagpieRepository;
import com.honeysense.magpie.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository extends MagpieRepository<User> {
    Page<User> findByNameLike(String userName, Pageable pageable);
    Page<User> findByPhoneLike(String userName, Pageable pageable);
    Optional<User> findByName(String userName);
    Optional<User> findByPhone(String phone);
}
