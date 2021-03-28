package com.honeysense.magpie.user.service;

import com.honeysense.magpie.framework.saas.service.MagpieService;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.user.entity.User;
import com.honeysense.magpie.user.entity.UserOAuth;
import com.honeysense.magpie.user.entity.refer.UserRefer;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface UserService extends MagpieService<User> {
    User insertPhone(String phone, UserRefer userRefer, Long directInvitorUserId);
    User insertName(String name, UserRefer userRefer, Long directInvitorUserId);
    User insertOAuth(UserOAuth.Type openType, String appId, String openId, UserRefer userRefer, Long directInvitorUserId);

    User findByPhone(String phone);
    User findByName(String name);
    User findByOAuth(UserOAuth.Type openType, String appId, String openId);

    boolean validUserPassword(Long userId, String password);

    MagpiePage<User> findByPhoneLike(String phone, PageRequest pageRequest);
    MagpiePage<User> findByNameLike(String name, PageRequest pageRequest);

    void deleteUser(String userId);
}
