package com.honeysense.magpie.user.service.impl;

import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.saas.service.impl.MagpieServiceImpl;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.user.entity.User;
import com.honeysense.magpie.user.entity.UserDeleted;
import com.honeysense.magpie.user.entity.UserRole;
import com.honeysense.magpie.user.entity.UserRelation;
import com.honeysense.magpie.user.repository.UserDeletedRepository;
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
    private final UserDeletedRepository userDeletedRepository;
    private final UserRelationRepository userRelationRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserDeletedRepository userDeletedRepository,
                           UserRelationRepository userRelationRepository) {
        super(userRepository);

        this.userRepository = userRepository;
        this.userDeletedRepository = userDeletedRepository;
        this.userRelationRepository = userRelationRepository;
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

        User user = userRepository.findByPhone(phone).orElse(null);
        if (user != null) {
            throw new MagpieException(MagpieException.Type.DUPLICATE, "phone");
        }

        user = User.builder().phone(phone).role(UserRole.CUSTOMER).enable(true).build();
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

        User user = userRepository.findByName(name).orElse(null);
        if (user != null) {
            throw new MagpieException(MagpieException.Type.DUPLICATE, "name");
        }

        user = User.builder().name(name).password(passwordEncoder.encode(password)).role(UserRole.CUSTOMER).enable(true).build();
        userRepository.save(user);

        insertUserRelation(user.getId(), userRefer.getDirectInvitorUserId());

        return user;
    }

    @Override
    public void updateRole(Long id, UserRole userRole) {
        if (!MagpieValidator.longId(id)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "id");
        }

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.NOT_FUND, map);
        }

        user.setRole(userRole);
        userRepository.save(user);
    }

    @Override
    public void updateEnable(Long id, Boolean enable) {
        if (!MagpieValidator.longId(id)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "id");
        }

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.NOT_FUND, map);
        }

        user.setEnable(Boolean.TRUE.equals(enable));
        userRepository.save(user);
    }

    @Override
    public void updateComment(Long id, String comment) {
        if (!MagpieValidator.longId(id)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "id");
        }

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.NOT_FUND, map);
        }

        user.setComment(comment);
        userRepository.save(user);
    }

    @Override
    public void updatePhone(Long id, String phone) {
        if (!MagpieValidator.longId(id)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "id");
        }

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.NOT_FUND, map);
        }

        User userByPhone = userRepository.findByPhone(phone).orElse(null);
        if (userByPhone != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("phone", phone);

            throw new MagpieException(MagpieException.Type.DUPLICATE, map);
        }

        user.setPhone(phone);
        userRepository.save(user);
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
    public boolean validUserIdAndPassword(Long id, String password) {
        if (!MagpieValidator.longId(id)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "id");
        }

        if (!MagpieValidator.password(password)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "password");
        }

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return false;
        }

        if (!MagpieValidator.password(password)) {
            return false;
        }

        return passwordEncoder.matches(password, user.getPassword());
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
    public void deleteUser(Long id) {
        if (!MagpieValidator.longId(id)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "id");
        }

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.NOT_FUND, map);
        }

        UserDeleted userDeleted = new UserDeleted();
        userDeleted.setUserId(user.getId());
        userDeleted.setPhone(user.getPhone());
        userDeleted.setPassword(user.getPassword());
        userDeleted.setRole(user.getRole());
        userDeleted.setComment(user.getComment());
        userDeletedRepository.save(userDeleted);

        userRepository.delete(user);
    }
}
