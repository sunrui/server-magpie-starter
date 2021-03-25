package com.honeysense.user.service;

import com.honeysense.magpie.saas.service.MagpieService;
import com.honeysense.magpie.entity.MagpiePage;
import com.honeysense.user.entity.User;
import com.honeysense.user.entity.UserOAuth;
import com.honeysense.user.entity.refer.UserRefer;

import java.util.Optional;

public interface UserService extends MagpieService<User> {
    User insertPhone(String phone, UserRefer userRefer, Long directInvitorUserId, Long indirectInvitorUserId);
    User insertName(String name, UserRefer userRefer, Long directInvitorUserId, Long indirectInvitorUserId);
    User insertOAuth(UserOAuth.Type openType, String appId, String openId, UserRefer userRefer, Long directInvitorUserId, Long indirectInvitorUserId);

    Optional<User> findByPhone(String phone);
    User findByName(String name);
    User findByOAuth(UserOAuth.Type openType, String appId, String openId);

    boolean localVerifyPassword(Long userId, String password);

    MagpiePage<User> findByPhoneLike(String phone, int page, int size);
    MagpiePage<User> findByNameLike(String name, int page, int size);

    void deleteUser(String userId);
}
