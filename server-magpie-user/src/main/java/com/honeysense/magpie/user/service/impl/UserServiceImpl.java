package com.honeysense.magpie.user.service.impl;

import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.saas.service.impl.MagpieServiceImpl;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.user.entity.User;
import com.honeysense.magpie.user.entity.UserThird;
import com.honeysense.magpie.user.entity.UserRelation;
import com.honeysense.magpie.user.repository.UserThirdRepository;
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
    private final UserThirdRepository userThirdRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRelationRepository userRelationRepository, UserThirdRepository userThirdRepository) {
        super(userRepository);

        this.userRepository = userRepository;
        this.userRelationRepository = userRelationRepository;
        this.userThirdRepository = userThirdRepository;
    }

    private void insertUserRelation(Long userId, Long directInvitorUserId) {
        Long indirectInvitorUserId = null;

        UserRefer userRefer = new UserRefer();
        if (!MagpieValidator.longId(directInvitorUserId)) {
            return;
        }

        if (MagpieValidator.longId(directInvitorUserId)) {
            Optional<User> userOptional = userRepository.findById(directInvitorUserId);
            if (userOptional.isEmpty()) {
                Map<String, Long> map = new HashMap<>();
                map.put("directInvitorUserId", directInvitorUserId);

                throw new MagpieException(MagpieException.Type.NOT_FUND, map);
            }
        }

        UserRelation userRelation = userRelationRepository.findByUserId(directInvitorUserId);
        if (userRelation == null) {
            Map<String, Long> map = new HashMap<>();
            map.put("directInvitorUserId", directInvitorUserId);

            throw new MagpieException(MagpieException.Type.NOT_FUND, map);
        }

        if (MagpieValidator.longId(userRelation.getDirectInvitorUserId())) {
            indirectInvitorUserId = userRelation.getDirectInvitorUserId();
        }

        userRelation = new UserRelation(userRefer);
        userRelation.setUserId(userId);
        userRelation.setDirectInvitorUserId(directInvitorUserId);
        userRelation.setIndirectInvitorUserId(indirectInvitorUserId);
        userRelationRepository.save(userRelation);
    }

    @Override
    public User insertPhone(String phone, UserRefer userRefer) {
        if (!MagpieValidator.phone(phone)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        MagpieValidator.object(userRefer);

        Optional<User> userOptional = userRepository.findByPhone(phone);
        if (userOptional.isPresent()) {
            throw new MagpieException(MagpieException.Type.DUPLICATE, "phone");
        }

        User user = User.builder().phone(phone).build();
        userRepository.save(user);

        insertUserRelation(user.getId(), userRefer.getDirectInvitorUserId());

        return user;
    }

    @Override
    public User insertName(String name, String password, UserRefer userRefer) {
        if (!MagpieValidator.enId(name)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        MagpieValidator.object(userRefer);

        Optional<User> userOptional = userRepository.findByName(name);
        if (userOptional.isPresent()) {
            throw new MagpieException(MagpieException.Type.DUPLICATE, "name");
        }

        User user = User.builder().name(name).password(passwordEncoder.encode(password)).build();
        userRepository.save(user);

        insertUserRelation(user.getId(), userRefer.getDirectInvitorUserId());

        return user;
    }

    @Override
    public User insertThird(UserThird.Type type, String appId, String openId, UserRefer userRefer) {
        if (type == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "type");
        }

        if (!MagpieValidator.enId(appId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "appId");
        }

        if (!MagpieValidator.enId(openId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "openId");
        }

        MagpieValidator.object(userRefer);

        Optional<UserThird> userOAuthOptional = userThirdRepository.findByTypeAndAppIdAndOpenId(type, appId, openId);
        if (userOAuthOptional.isPresent()) {
            Map<String, String> map = new HashMap<>();
            map.put("type", type.name());
            map.put("appId", appId);
            map.put("openId", openId);

            throw new MagpieException(MagpieException.Type.DUPLICATE, map);
        }

        User user = User.builder().build();
        userRepository.save(user);

        UserThird userThird = UserThird.builder()
                .user(user)
                .type(type)
                .appId(appId)
                .openId(openId)
                .build();
        userThirdRepository.save(userThird);

        insertUserRelation(user.getId(), userRefer.getDirectInvitorUserId());

        return user;
    }

    @Override
    public User findByPhone(String phone) {
        if (!MagpieValidator.phone(phone)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        return userRepository.findByPhone(phone).orElse(null);
    }

    @Override
    public User findByName(String name) {
        if (!MagpieValidator.enId(name)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "name");
        }

        return userRepository.findByName(name).orElse(null);
    }

    @Override
    public User findByThirdTypeAndAppIdAndOpenId(UserThird.Type type, String appId, String openId) {
        if (type == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "type");
        }

        if (!MagpieValidator.enId(appId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "appId");
        }

        if (!MagpieValidator.enId(openId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "openId");
        }

        Optional<UserThird> userOpen = userThirdRepository.findByTypeAndAppIdAndOpenId(type, appId, openId);
        if (userOpen.isEmpty()) {
            return null;
        }

        return userOpen.get().getUser();
    }

    @Override
    public boolean validUserIdAndPassword(Long userId, String password) {
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
    public MagpiePage<User> findByPhoneLike(String phone, PageRequest pageRequest) {
        if (!MagpieValidator.phone(phone)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        Page<User> elements = userRepository.findByPhoneLike(phone, pageRequest);
        return new MagpiePage<>(elements);
    }

    @Override
    public MagpiePage<User> findByNameLike(String name, PageRequest pageRequest) {
        if (!MagpieValidator.enId(name)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "name");
        }

        Page<User> elements = userRepository.findByNameLike(name, pageRequest);
        return new MagpiePage<>(elements);
    }

    @Override
    public void deleteUser(String userId) {

    }
}
