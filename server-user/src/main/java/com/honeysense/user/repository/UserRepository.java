package com.honeysense.user.repository;

import com.honeysense.magpie.saas.repository.MagpieRepository;
import com.honeysense.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository extends MagpieRepository<User> {
    Page<User> findByNameLike(String userName, Pageable pageable);
    Page<User> findByPhoneLike(String userName, Pageable pageable);
    Optional<User> findByName(String userName);
    Optional<User> findByPhone(String phone);
}
