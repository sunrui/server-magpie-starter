package com.honeysense.sms.tests;

import com.honeysense.magpie.entity.MagpiePage;
import com.honeysense.user.entity.User;
import com.honeysense.user.entity.UserOAuth;
import com.honeysense.user.entity.UserRelation;
import com.honeysense.user.entity.refer.UserRefer;
import com.honeysense.user.service.UserRelationService;
import com.honeysense.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserServiceTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRelationService userRelationService;

    @Test
    public void testUser() {
        User user = User.builder()
                .name("sunrui")
                .phone("15068860507")
                .password("password")
                .build();

        user.dump();

        MagpiePage<User> userMagpiePage = userService.findAll(0, 9);
        for (User one : userMagpiePage.getElements()) {
            one.dump();

            userService.deleteById(one.getId());
        }

        userService.save(user);

        Long userId = null;

        userMagpiePage = userService.findAll(0, 9);
        for (User one : userMagpiePage.getElements()) {
            one.dump();

            one.setName("sunrui");
            userId = one.getId();

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            userService.save(one);
        }

        Assert.assertNotNull("userId is null", userId);

        user = userService.findById(userId);
        user.dump();
    }

    @Test
    void testUserRegister() {
        UserRefer userRefer = new UserRefer();
        userRefer.setIp("127.0.0.1");
        userRefer.setUserAgent("ua");

        User user = userService.insertName("name2", userRefer, null, null);
        user.dump();

        user = userService.insertOAuth(UserOAuth.Type.WECHAT, "appId", "openId", userRefer, null, null);
        user.dump();

        user = userService.insertPhone("15068860057", userRefer, null, null);
        user.dump();
    }

    @Test
    void testAll() {
        MagpiePage<User> userMagpiePage = userService.findAll(0, 99);
        for (User user : userMagpiePage.getElements()) {
            user.dump();
        }

        MagpiePage<UserRelation> userReferMagpiePage = userRelationService.findAll(0, 99);
        for (UserRelation userRelation : userReferMagpiePage.getElements()) {
            userRelation.dump();
        }
    }
}
