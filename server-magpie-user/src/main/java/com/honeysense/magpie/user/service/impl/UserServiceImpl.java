package com.honeysense.magpie.user.service.impl;

import com.honeysense.magpie.framework.entity.MagpieException;
import com.honeysense.magpie.framework.saas.service.impl.MagpieServiceImpl;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.entity.MagpiePage;
import com.honeysense.magpie.user.entity.User;
import com.honeysense.magpie.user.entity.UserOAuth;
import com.honeysense.magpie.user.entity.UserRelation;
import com.honeysense.magpie.user.repository.UserOAuthRepository;
import com.honeysense.magpie.user.repository.UserRelationRepository;
import com.honeysense.magpie.user.repository.UserRepository;
import com.honeysense.magpie.user.service.UserService;
import com.honeysense.magpie.user.entity.refer.UserRefer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl extends MagpieServiceImpl<User> implements UserService {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    private final UserRelationRepository userRelationRepository;
    private final UserOAuthRepository userOAuthRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRelationRepository userRelationRepository, UserOAuthRepository userOAuthRepository) {
        super(userRepository);

        this.userRepository = userRepository;
        this.userRelationRepository = userRelationRepository;
        this.userOAuthRepository = userOAuthRepository;
    }

    @Override
    public User insertPhone(String phone, UserRefer userRefer, Long directInvitorUserId, Long indirectInvitorUserId) {
        if (!MagpieValidator.phone(phone)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        MagpieValidator.object(userRefer);

        Optional<User> one;

        if (MagpieValidator.longId(directInvitorUserId)) {
            one = userRepository.findById(directInvitorUserId);
            if (one.isEmpty()) {
                Map<String, Long> map = new HashMap<>();
                map.put("directInvitorUserId", directInvitorUserId);

                throw new MagpieException(MagpieException.Type.NOT_FUND, map);
            }
        }

        if (MagpieValidator.longId(indirectInvitorUserId)) {
            one = userRepository.findById(indirectInvitorUserId);
            if (one.isEmpty()) {
                Map<String, Long> map = new HashMap<>();
                map.put("indirectInvitorUserId", indirectInvitorUserId);

                throw new MagpieException(MagpieException.Type.NOT_FUND, map);
            }
        }

        one = userRepository.findByPhone(phone);
        if (one.isPresent()) {
            throw new MagpieException(MagpieException.Type.DUPLICATE, "phone");
        }

        User user = User.builder().phone(phone).build();
        userRepository.save(user);

        UserRelation userRelation = new UserRelation(userRefer);
        userRelation.setUserId(user.getId());
        userRelation.setDirectInvitorUserId(directInvitorUserId);
        userRelation.setIndirectInvitorUserId(indirectInvitorUserId);
        userRelationRepository.save(userRelation);

        return user;
    }

    @Override
    public User insertName(String name, UserRefer userRefer, Long directInvitorUserId, Long indirectInvitorUserId) {
        if (!MagpieValidator.enId(name)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        MagpieValidator.object(userRefer);

        Optional<User> one;

        if (MagpieValidator.longId(directInvitorUserId)) {
            one = userRepository.findById(directInvitorUserId);
            if (one.isEmpty()) {
                Map<String, Long> map = new HashMap<>();
                map.put("directInvitorUserId", directInvitorUserId);

                throw new MagpieException(MagpieException.Type.NOT_FUND, map);
            }
        }

        if (MagpieValidator.longId(indirectInvitorUserId)) {
            one = userRepository.findById(indirectInvitorUserId);
            if (one.isEmpty()) {
                Map<String, Long> map = new HashMap<>();
                map.put("indirectInvitorUserId", indirectInvitorUserId);

                throw new MagpieException(MagpieException.Type.NOT_FUND, map);
            }
        }

        one = userRepository.findByName(name);
        if (one.isPresent()) {
            throw new MagpieException(MagpieException.Type.DUPLICATE, "name");
        }

        User user = User.builder().name(name).build();
        userRepository.save(user);

        UserRelation userRelation = new UserRelation(userRefer);
        userRelation.setUserId(user.getId());
        userRelation.setDirectInvitorUserId(directInvitorUserId);
        userRelation.setIndirectInvitorUserId(indirectInvitorUserId);
        userRelationRepository.save(userRelation);

        return user;
    }

    @Override
    public User insertOAuth(UserOAuth.Type openType, String appId, String openId, UserRefer userRefer, Long directInvitorUserId, Long indirectInvitorUserId) {
        if (openType == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "openType");
        }

        if (!MagpieValidator.enId(appId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "appId");
        }

        if (!MagpieValidator.enId(openId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "openId");
        }

        MagpieValidator.object(userRefer);

        Optional<User> one;

        if (MagpieValidator.longId(directInvitorUserId)) {
            one = userRepository.findById(directInvitorUserId);
            if (one.isEmpty()) {
                Map<String, Long> map = new HashMap<>();
                map.put("directInvitorUserId", directInvitorUserId);

                throw new MagpieException(MagpieException.Type.NOT_FUND, map);
            }
        }

        if (MagpieValidator.longId(indirectInvitorUserId)) {
            one = userRepository.findById(indirectInvitorUserId);
            if (one.isEmpty()) {
                Map<String, Long> map = new HashMap<>();
                map.put("indirectInvitorUserId", indirectInvitorUserId);

                throw new MagpieException(MagpieException.Type.NOT_FUND, map);
            }
        }

        Optional<UserOAuth> userOpenOne = userOAuthRepository.findByTypeAndAppIdAndOpenId(openType, appId, openId);
        if (userOpenOne.isPresent()) {
            Map<String, String> map = new HashMap<>();
            map.put("openType", openType.name());
            map.put("appId", appId);
            map.put("openId", openId);

            throw new MagpieException(MagpieException.Type.DUPLICATE, map);
        }

        User user = User.builder().build();
        userRepository.save(user);

        UserOAuth userOAuth = UserOAuth.builder()
                .user(user)
                .type(openType)
                .appId(appId)
                .openId(openId)
                .build();
        userOAuthRepository.save(userOAuth);

        UserRelation userRelation = new UserRelation(userRefer);
        userRelation.setUserId(user.getId());
        userRelation.setDirectInvitorUserId(directInvitorUserId);
        userRelation.setIndirectInvitorUserId(indirectInvitorUserId);
        userRelationRepository.save(userRelation);

        return user;
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        if (!MagpieValidator.phone(phone)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        return userRepository.findByPhone(phone);
    }

    @Override
    public User findByName(String name) {
        if (!MagpieValidator.enId(name)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "name");
        }

        return userRepository.findByName(name).orElse(null);
    }

    @Override
    public User findByOAuth(UserOAuth.Type openType, String appId, String openId) {
        if (openType == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "openType");
        }

        if (!MagpieValidator.enId(appId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "appId");
        }

        if (!MagpieValidator.enId(openId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "openId");
        }

        Optional<UserOAuth> userOpen = userOAuthRepository.findByTypeAndAppIdAndOpenId(openType, appId, openId);
        if (userOpen.isEmpty()) {
            return null;
        }

        return userOpen.get().getUser();
    }

    @Override
    public boolean localVerifyPassword(Long userId, String password) {
        if (!MagpieValidator.longId(userId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "userId");
        }

        if (!MagpieValidator.password(password)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "password");
        }

        Optional<User> one = userRepository.findById(userId);
        if (one.isEmpty()) {
            return false;
        }

        if (!MagpieValidator.password(password)) {
            return false;
        }

        return passwordEncoder.matches(password, one.get().getPassword());
    }

    @Override
    public MagpiePage<User> findByPhoneLike(String phone, int page, int size) {
        if (!MagpieValidator.phone(phone)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        if (page < 0) {
            Map<String, Integer> map = new HashMap<>();
            map.put("page", page);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (size <= 0) {
            Map<String, Integer> map = new HashMap<>();
            map.put("size", size);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        Page<User> elements = userRepository.findByPhoneLike(phone, PageRequest.of(page, size));
        return new MagpiePage<>(elements);
    }

    @Override
    public MagpiePage<User> findByNameLike(String name, int page, int size) {
        if (!MagpieValidator.enId(name)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "name");
        }

        if (page < 0) {
            Map<String, Integer> map = new HashMap<>();
            map.put("page", page);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (size <= 0) {
            Map<String, Integer> map = new HashMap<>();
            map.put("size", size);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        Page<User> elements = userRepository.findByNameLike(name, PageRequest.of(page, size));
        return new MagpiePage<>(elements);
    }

    @Override
    public void deleteUser(String userId) {

    }
}
