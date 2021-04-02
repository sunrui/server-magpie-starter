package com.honeysense.magpie.user.service;

import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.saas.service.MagpieService;
import com.honeysense.magpie.user.entity.User;
import com.honeysense.magpie.user.entity.UserRole;
import com.honeysense.magpie.user.entity.refer.UserRefer;
import org.springframework.data.domain.PageRequest;

public interface UserService extends MagpieService<User> {
    User insertPhone(String phone, UserRefer userRefer);
    User insertName(String name, String password, UserRefer userRefer);

    void updateRole(Long id, UserRole userRole);
    void updateEnable(Long id, Boolean enable);
    void updateComment(Long id, String comment);

    void updatePhone(Long id, String phone);

    User findByPhone(String phone);
    User findByName(String name);

    boolean validUserIdAndPassword(Long id, String password);

    MagpiePage<User> findByPhoneLike(String phone, PageRequest pageRequest);
    MagpiePage<User> findByNameLike(String name, PageRequest pageRequest);

    void deleteUser(Long id);
}
